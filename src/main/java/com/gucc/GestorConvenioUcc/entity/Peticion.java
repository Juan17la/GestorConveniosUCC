package com.gucc.GestorConvenioUcc.entity;

import com.gucc.GestorConvenioUcc.enums.EstadoConvenio;
import com.gucc.GestorConvenioUcc.enums.EstadoPeticion;
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
@Table(name = "peticiones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Peticion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombrePeticion;

    @Column(nullable = false)
    private LocalDateTime fechaInicio;

    @Column(nullable = false)
    private LocalDateTime fechaFinalizacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPeticion estado;

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
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "peticion", cascade = CascadeType.ALL, orphanRemoval = true)
    private RevisionJuridica revisionJuridica;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "peticion", cascade = CascadeType.ALL, orphanRemoval = true)
    private FirmaConvenioCampus firmaConvenioCampus;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "peticion", cascade = CascadeType.ALL, orphanRemoval = true)
    private FirmaConvenioNacional firmaConvenioNacional;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "peticion", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Documento> documentos = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "peticionCreacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private Convenio convenio;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
        if (estado == null) {
            estado = EstadoPeticion.EN_REVISION_JURIDICA;
        }
    }

}
