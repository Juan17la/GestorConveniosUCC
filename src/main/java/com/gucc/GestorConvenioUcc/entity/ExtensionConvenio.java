package com.gucc.GestorConvenioUcc.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "extensiones_convenio")
public class ExtensionConvenio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "fecha_extension", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaExtension;
    
    @Column(name = "nueva_fecha_final", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date nuevaFechaFinal;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
        name = "extension_documentos",
        joinColumns = @JoinColumn(name = "extension_id"),
        inverseJoinColumns = @JoinColumn(name = "documento_id")
    )
    private List<DocumentoConvenio> documentosExtension;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "convenio_id", nullable = false)
    private Convenio convenio;
}
