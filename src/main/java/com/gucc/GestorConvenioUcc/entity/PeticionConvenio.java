package com.gucc.GestorConvenioUcc.entity;

import com.gucc.GestorConvenioUcc.enums.EstadoPeticion;
import com.gucc.GestorConvenioUcc.enums.TipoConvenio;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "peticiones_convenio")
public class PeticionConvenio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "fecha_creacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion = new Date();
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_convenio", nullable = false)
    private TipoConvenio tipo;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPeticion estado = EstadoPeticion.EN_REVISION_ADMIN;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creador_id", nullable = false)
    private Usuario creador;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campus_id", nullable = false)
    private Campus campus;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "revision_administrativa_id")
    private RevisionAdministrativa revisionAdministrativa;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "revision_juridica_id")
    private RevisionJuridica revisionJuridica;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "firma_convenio_id")
    private FirmaConvenio firmaConvenio;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
        name = "peticion_documentos",
        joinColumns = @JoinColumn(name = "peticion_id"),
        inverseJoinColumns = @JoinColumn(name = "documento_id")
    )
    private List<DocumentoConvenio> documentos;
}
