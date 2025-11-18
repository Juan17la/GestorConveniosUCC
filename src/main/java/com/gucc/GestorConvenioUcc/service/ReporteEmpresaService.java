package com.gucc.GestorConvenioUcc.service;

import com.gucc.GestorConvenioUcc.dto.ReporteEmpresaDTO;
import com.gucc.GestorConvenioUcc.entity.Convenio;
import com.gucc.GestorConvenioUcc.entity.Empresa;
import com.gucc.GestorConvenioUcc.entity.ReporteEmpresa;
import com.gucc.GestorConvenioUcc.repository.ConvenioRepository;
import com.gucc.GestorConvenioUcc.repository.EmpresaRepository;
import com.gucc.GestorConvenioUcc.repository.ReporteEmpresaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReporteEmpresaService {

    private final ReporteEmpresaRepository reporteRepository;
    private final ConvenioRepository convenioRepository;
    private final EmpresaRepository empresaRepository;

    /**
     * Crea un nuevo reporte de empresa para un convenio específico
     */
    @Transactional
    public ReporteEmpresaDTO crearReporte(ReporteEmpresaDTO dto) {

        // Validar que el convenio existe
        Convenio convenio = convenioRepository.findById(dto.getConvenioId())
                .orElseThrow(() -> new RuntimeException("Convenio no encontrado con ID: " + dto.getConvenioId()));

        // Validar que la empresa existe
        Empresa empresa = empresaRepository.findById(dto.getEmpresaId())
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada con ID: " + dto.getEmpresaId()));

        // Validar que la empresa del convenio coincida con la empresa del reporte
        if (convenio.getEmpresa() != null && !convenio.getEmpresa().getId().equals(empresa.getId())) {
            throw new RuntimeException("La empresa del reporte no coincide con la empresa del convenio");
        }

        // Crear el reporte
        ReporteEmpresa reporte = ReporteEmpresa.builder()
                .fechaReporte(dto.getFechaReporte() != null ? dto.getFechaReporte() : LocalDateTime.now())
                .descripcion(dto.getDescripcion())
                .actividades(dto.getActividades())
                .asistencia(dto.getAsistencia())
                .convenio(convenio)
                .empresa(empresa)
                .build();

        ReporteEmpresa saved = reporteRepository.save(reporte);

        log.info("✅ Reporte creado para empresa {} en convenio {}",
                empresa.getNombre(), convenio.getNombreConvenio());

        return mapToDTO(saved);
    }

    /**
     * Obtiene todos los reportes de un convenio específico
     */
    @Transactional(readOnly = true)
    public List<ReporteEmpresaDTO> obtenerReportesPorConvenio(Long convenioId) {
        List<ReporteEmpresa> reportes = reporteRepository.findByConvenioId(convenioId);
        return reportes.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene todos los reportes de una empresa específica
     */
    @Transactional(readOnly = true)
    public List<ReporteEmpresaDTO> obtenerReportesPorEmpresa(Long empresaId) {
        List<ReporteEmpresa> reportes = reporteRepository.findByEmpresaId(empresaId);
        return reportes.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene todos los reportes de una empresa en un convenio específico
     */
    @Transactional(readOnly = true)
    public List<ReporteEmpresaDTO> obtenerReportesPorEmpresaYConvenio(Long empresaId, Long convenioId) {
        List<ReporteEmpresa> reportes = reporteRepository.findByEmpresaIdAndConvenioId(empresaId, convenioId);
        return reportes.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Actualiza un reporte existente
     */
    @Transactional
    public ReporteEmpresaDTO actualizarReporte(Long id, ReporteEmpresaDTO dto) {

        ReporteEmpresa reporte = reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado con ID: " + id));

        // Actualizar campos
        if (dto.getDescripcion() != null) {
            reporte.setDescripcion(dto.getDescripcion());
        }
        if (dto.getActividades() != null) {
            reporte.setActividades(dto.getActividades());
        }
        if (dto.getAsistencia() != null) {
            reporte.setAsistencia(dto.getAsistencia());
        }
        if (dto.getFechaReporte() != null) {
            reporte.setFechaReporte(dto.getFechaReporte());
        }

        ReporteEmpresa updated = reporteRepository.save(reporte);

        log.info("✏️ Reporte {} actualizado", id);

        return mapToDTO(updated);
    }

    /**
     * Elimina un reporte
     */
    @Transactional
    public void eliminarReporte(Long id) {
        if (!reporteRepository.existsById(id)) {
            throw new RuntimeException("Reporte no encontrado con ID: " + id);
        }
        reporteRepository.deleteById(id);
        log.info("🗑️ Reporte {} eliminado", id);
    }

    /**
     * Mapea entidad a DTO
     */
    private ReporteEmpresaDTO mapToDTO(ReporteEmpresa reporte) {
        return ReporteEmpresaDTO.builder()
                .id(reporte.getId())
                .fechaReporte(reporte.getFechaReporte())
                .descripcion(reporte.getDescripcion())
                .actividades(reporte.getActividades())
                .asistencia(reporte.getAsistencia())
                .empresaId(reporte.getEmpresa().getId())
                .empresaNombre(reporte.getEmpresa().getNombre())
                .convenioId(reporte.getConvenio().getId())
                .convenioNombre(reporte.getConvenio().getNombreConvenio())
                .build();
    }
}
