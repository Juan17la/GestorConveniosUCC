package com.gucc.GestorConvenioUcc.dto;

import com.gucc.GestorConvenioUcc.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String apellidos;
    private String contrasena;
    private String email;
    private Long campusId;
    private String campusNombre;
    private Roles rol;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaUltimoLogin;
}
