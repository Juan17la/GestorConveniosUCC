package com.gucc.GestorConvenioUcc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentoDTO {
    private Long id;
    private String nombreOriginal;
    private String url;
    private String tipoArchivo;
    private Long subidoPorId;
    private LocalDateTime fechaSubida;
    private Long peticionId;
    private Long convenioId;
}
