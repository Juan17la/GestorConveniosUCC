package com.gucc.GestorConvenioUcc.dto;

import com.gucc.GestorConvenioUcc.enums.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String nombre;
    private String username;
    private String contrasena;
    private Rol rol;
    private Long campusId;
}
