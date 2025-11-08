package com.gucc.GestorConvenioUcc.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class DocumentoConvenioDTO {
    private Long id;
    private String nombreArchivo;
    private String url;
    private String tipoArchivo;
    private Long subidoPorId;
    private Date fechaSubida;
}
