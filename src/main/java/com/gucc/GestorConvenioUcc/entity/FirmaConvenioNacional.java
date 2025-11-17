package com.gucc.GestorConvenioUcc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "firmas_convenio_nacional")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FirmaConvenioNacional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "revisor_id", nullable = false)
    private Usuario revisor;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "peticion_id", nullable = false)
    private Peticion peticion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "documento_firmado_id", nullable = false)
    private Documento documentoFirmado;

    @Column(nullable = false)
    private Boolean aprobada;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String mensajeRechazo;
}
