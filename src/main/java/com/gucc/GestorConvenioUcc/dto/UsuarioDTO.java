package com.gucc.GestorConvenioUcc.dto;

import com.gucc.GestorConvenioUcc.enums.Rol;
import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String username;
    private Rol rol;
    private Long campusId;
    private String campusNombre;
}
