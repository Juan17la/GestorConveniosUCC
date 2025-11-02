package com.gucc.GestorConvenioUcc.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DocumentoConvenioDTO {
    private Long id;
    private String nombreArchivo;
    private String url;
    private String tipoArchivo;
    private Long subidoPorId;
    private String subidoPorNombre;
    private LocalDateTime fechaSubida;
}
