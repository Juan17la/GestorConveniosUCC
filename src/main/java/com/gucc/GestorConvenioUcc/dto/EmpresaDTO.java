package com.gucc.GestorConvenioUcc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpresaDTO {
    private Long id;
    private String nombre;
    private String nit;
    private String direccion;
    private String telefono;
    private String representante;
}
