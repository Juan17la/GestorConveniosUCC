package com.gucc.GestorConvenioUcc.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "firmas_convenio")
public class FirmaConvenio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "fecha_firma")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFirma;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "directivo_firmante_id")
    private Usuario directivoFirmante;
    
    private Boolean firmado = false;
    
    @Column(name = "razon_rechazo")
    private String razonRechazo;
}
