package com.gucc.GestorConvenioUcc.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rangos_advertencia")
public class RangoAdvertencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "administrador_id")
    private Usuario administrador;
    
    @Column(nullable = false)
    private Integer diasAmarillo;
    
    @Column(nullable = false)
    private Integer diasNaranja;
    
    @Column(nullable = false)
    private Integer diasRojo;
    
    @Column(nullable = false)
    private Boolean global = false;
}
