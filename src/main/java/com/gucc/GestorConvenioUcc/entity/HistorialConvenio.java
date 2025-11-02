package com.gucc.GestorConvenioUcc.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "historial_convenios")
public class HistorialConvenio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "convenio_id", nullable = false)
    private Convenio convenio;
    
    @ManyToMany
    @JoinTable(
        name = "historial_documentos_convenio",
        joinColumns = @JoinColumn(name = "historial_id"),
        inverseJoinColumns = @JoinColumn(name = "documento_id")
    )
    private List<DocumentoConvenio> historialDocumentos;
}
