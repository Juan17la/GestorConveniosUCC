package com.gucc.GestorConvenioUcc.entity;

import com.gucc.GestorConvenioUcc.enums.EstadoConvenio;
import com.gucc.GestorConvenioUcc.enums.TipoConvenio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "convenios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Convenio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombreConvenio;

    @Column(nullable = false)
    private LocalDateTime fechaInicio;

    @Column(nullable = false)
    private LocalDateTime fechaFinalizacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoConvenio tipo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creado_por_id", nullable = false)
    private Usuario creadoPor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "campus_id", nullable = false)
    private Campus campus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supervisado_por_id")
    private Usuario supervisadoPor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "peticion_creacion_id", nullable = false)
    private Peticion peticionCreacion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "convenio", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Documento> documentos = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "convenio", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ReporteEmpresa> reportesEmpresa = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "convenio", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<AlertaVencimiento> alertasVencimiento = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "convenioOriginal", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<RenovacionConvenio> renovaciones = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoConvenio estado;

    @Column(name = "ignorado", nullable = false)
    private boolean ignorado = false;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
        if (estado == null) {
            estado = EstadoConvenio.ACTIVO;
        }
    }

    // ⚠️ CORRECCIÓN IMPORTANTE: Este método estaba devolviendo null
    // Lombok ya genera getIgnorado() por el campo 'ignorado'
    // Por lo tanto, ELIMINA este método o corrígelo así:
    public Boolean getIgnorado() {
        return this.ignorado;
    }

    public boolean isIgnorado() {
        return false;
    }
}