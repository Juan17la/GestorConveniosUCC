package com.gucc.GestorConvenioUcc.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "revisiones_juridicas")
public class RevisionJuridica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "fecha_revision")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRevision;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "revisor_juridico_id")
    private Usuario revisorJuridico;
    
    private Boolean aprobada;
    
    @Column(name = "razon_rechazo")
    private String razonRechazo;
}
