package com.gucc.GestorConvenioUcc.dto;

import lombok.Data;

@Data
public class RangoAdvertenciaDTO {
    private Long id;
    private Long administradorId;
    private String administradorNombre;
    private Integer diasAmarillo;
    private Integer diasNaranja;
    private Integer diasRojo;
    private Boolean global;
}
