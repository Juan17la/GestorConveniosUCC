package com.gucc.GestorConvenioUcc.scheduler;

import com.gucc.GestorConvenioUcc.entity.AlertaVencimiento;
import com.gucc.GestorConvenioUcc.entity.Convenio;
import com.gucc.GestorConvenioUcc.entity.Peticion;
import com.gucc.GestorConvenioUcc.entity.RenovacionConvenio;
import com.gucc.GestorConvenioUcc.enums.EstadoAlerta;
import com.gucc.GestorConvenioUcc.enums.EstadoConvenio;
import com.gucc.GestorConvenioUcc.enums.EstadoPeticion;
import com.gucc.GestorConvenioUcc.repository.AlertaVencimientoRepository;
import com.gucc.GestorConvenioUcc.repository.ConvenioRepository;
import com.gucc.GestorConvenioUcc.repository.PeticionRepository;
import com.gucc.GestorConvenioUcc.repository.RenovacionConvenioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConvenioTracker {

    private final ConvenioRepository convenioRepo;
    private final RenovacionConvenioRepository renovacionRepo;
    private final PeticionRepository peticionRepo;
    private final AlertaVencimientoRepository alertaRepo;

    /**
     * Se ejecuta todos los días a medianoche (00:00:00)
     * Expresión cron: segundo minuto hora día mes día-semana
     */
    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void procesarEstadoConvenios() {
        log.info("Iniciando proceso de actualización de estados de convenios...");

        LocalDate hoy = LocalDate.now();

        List<Convenio> convenios = convenioRepo.findByEstadoIn(
                List.of(EstadoConvenio.ACTIVO, EstadoConvenio.POR_VENCER, EstadoConvenio.VENCIDO)
        );

        log.info("Convenios a procesar: {}", convenios.size());

        for (Convenio c : convenios) {

            // =======================
            // 1. ¿CONVENIO IGNORADO?
            // =======================
            if (Boolean.TRUE.equals(c.getIgnorado())) {
                log.debug("⏭Convenio {} ignorado manualmente", c.getNombreConvenio());
                continue;
            }

            // =======================
            // 2. ¿TIENE RENOVACIÓN?
            // =======================
            RenovacionConvenio ultimaRenovacion =
                    renovacionRepo.findTopByConvenioOriginalIdOrderByFechaCreacionDesc(c.getId());

            if (ultimaRenovacion != null) {
                LocalDate nuevaFecha = ultimaRenovacion.getFechaFinalizacion();

                // Solo actualizar si realmente cambió
                if (!c.getFechaFinalizacion().toLocalDate().equals(nuevaFecha)) {
                    c.setFechaFinalizacion(nuevaFecha.atStartOfDay());
                    c.setEstado(EstadoConvenio.ACTIVO);

                    log.info("CONVENIO RENOVADO: {} → nueva fecha: {}",
                            c.getNombreConvenio(), nuevaFecha);

                    convenioRepo.save(c);
                }
                // Una vez renovado, saltamos la lógica de expiración
                continue;
            }

            // =======================
            // 3. CALCULAR TIEMPO RESTANTE
            // =======================
            LocalDate fin = c.getFechaFinalizacion().toLocalDate();
            long diasRestantes = ChronoUnit.DAYS.between(hoy, fin);
            long mesesRestantes = ChronoUnit.MONTHS.between(hoy, fin);

            EstadoConvenio estadoAnterior = c.getEstado();

            // =======================
            // 4. ACTUALIZAR ESTADO DEL CONVENIO
            // =======================

            // Si ya pasó la fecha de finalización → VENCIDO
            if (diasRestantes < 0) {
                if (c.getEstado() != EstadoConvenio.VENCIDO) {
                    c.setEstado(EstadoConvenio.VENCIDO);
                    log.warn("CONVENIO VENCIDO: {} (pasó {} días)",
                            c.getNombreConvenio(), Math.abs(diasRestantes));
                }
            }
            // Si faltan 6 meses o menos → POR_VENCER
            else if (mesesRestantes <= 6) {
                if (c.getEstado() != EstadoConvenio.POR_VENCER) {
                    c.setEstado(EstadoConvenio.POR_VENCER);
                    log.warn("CONVENIO POR VENCER: {} (quedan {} meses / {} días)",
                            c.getNombreConvenio(), mesesRestantes, diasRestantes);
                }
            }
            // Si faltan más de 6 meses → ACTIVO
            else {
                if (c.getEstado() != EstadoConvenio.ACTIVO) {
                    c.setEstado(EstadoConvenio.ACTIVO);
                    log.info("CONVENIO REACTIVADO: {} (quedan {} meses)",
                            c.getNombreConvenio(), mesesRestantes);
                }
            }

            // =======================
            // 5. VERIFICAR SI HAY RENOVACIÓN EN CURSO
            // =======================
            boolean tieneRenovacionEnCurso = tieneRenovacionPendiente(c.getId());

            // =======================
            // 6. GENERAR ALERTAS (si NO hay renovación en curso)
            // =======================
            if (!tieneRenovacionEnCurso) {
                generarAlertaSiCorresponde(c, mesesRestantes, diasRestantes);
            } else {
                log.debug("Convenio {} tiene renovación en curso, no se genera alerta",
                        c.getNombreConvenio());
            }

            // =======================
            // 7. GUARDAR CAMBIOS
            // =======================
            if (estadoAnterior != c.getEstado()) {
                convenioRepo.save(c);
                log.info("Estado actualizado: {} → {} para convenio {}",
                        estadoAnterior, c.getEstado(), c.getNombreConvenio());
            }
        }

        log.info("Proceso de actualización de convenios completado");
    }

    /**
     * Verifica si el convenio tiene alguna petición de renovación que NO esté APROBADA
     */
    private boolean tieneRenovacionPendiente(Long convenioId) {
        List<Peticion> peticiones = peticionRepo.findByConvenioId(convenioId);

        return peticiones.stream()
                .anyMatch(p -> p.getEstado() != EstadoPeticion.APROBADA);
    }

    /**
     * Genera alertas cuando el convenio está a 6, 3 o 1 mes de vencer
     */
    private void generarAlertaSiCorresponde(Convenio convenio, long mesesRestantes, long diasRestantes) {

        boolean debeGenerarAlerta = false;
        String tipoAlerta = "";

        // Verificar si corresponde generar alerta
        if (mesesRestantes == 6) {
            debeGenerarAlerta = true;
            tipoAlerta = "6 MESES";
        } else if (mesesRestantes == 3) {
            debeGenerarAlerta = true;
            tipoAlerta = "3 MESES";
        } else if (mesesRestantes == 1) {
            debeGenerarAlerta = true;
            tipoAlerta = "1 MES";
        } else if (diasRestantes == 14) {
            debeGenerarAlerta = true;
            tipoAlerta = "14 DÍAS";
        }

        if (debeGenerarAlerta) {
            // Verificar si ya existe una alerta similar reciente (últimas 24 horas)
            LocalDateTime hace24Horas = LocalDateTime.now().minusHours(24);
            boolean yaExisteAlerta = alertaRepo.existsByConvenioIdAndFechaGeneradaAfter(
                    convenio.getId(), hace24Horas);

            if (!yaExisteAlerta) {
                // Crear nueva alerta
                AlertaVencimiento alerta = AlertaVencimiento.builder()
                        .convenio(convenio)
                        .enviadaA(convenio.getSupervisadoPor() != null
                                ? convenio.getSupervisadoPor()
                                : convenio.getCreadoPor())
                        .fechaGenerada(LocalDateTime.now())
                        .estado(EstadoAlerta.PENDIENTE)
                        .build();

                alertaRepo.save(alerta);

                log.warn("ALERTA GENERADA: Convenio '{}' vence en {} ({} días restantes)",
                        convenio.getNombreConvenio(), tipoAlerta, diasRestantes);
            }
        }
    }

    /**
     * OPCIONAL: Método que se ejecuta cada hora para logs más frecuentes
     * Puedes descomentar si necesitas revisiones más frecuentes
     */
    // @Scheduled(cron = "0 0 * * * *")
    // public void verificacionHoraria() {
    //     log.debug("Verificación horaria de convenios...");
    // }

    @Scheduled(fixedRate = 120000) // 120 segundos
    public void verificacionPruebas() {
        log.info("[MODO PRUEBA] Ejecutando verificación cada 120 segundos...");
        procesarEstadoConvenios();
    }

    /**
     * OPCIONAL: Para testing - se ejecuta cada 5 minutos
     * Útil durante desarrollo, comentar en producción
     */
    // @Scheduled(fixedRate = 300000) //
    // public void verificacionFrecuente() {
    //     procesarEstadoConvenios();
    // }
}

