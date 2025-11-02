package com.gucc.GestorConvenioUcc.dto;

import com.gucc.GestorConvenioUcc.enums.TipoConvenio;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class ConvenioDTO {
    private Long id;
    private String nombre;
    private TipoConvenio tipo;
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private Boolean activo;
    private Long supervisorId;
    private String supervisorNombre;
    private RangoAdvertenciaDTO rangoAdvertenciaPersonalizado;
    private List<ExtensionConvenioDTO> extensiones;
}
