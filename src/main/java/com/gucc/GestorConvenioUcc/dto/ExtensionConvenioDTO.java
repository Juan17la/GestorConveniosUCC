package com.gucc.GestorConvenioUcc.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class ExtensionConvenioDTO {
    private Long id;
    private LocalDate fechaExtension;
    private LocalDate nuevaFechaFinal;
    private List<DocumentoConvenioDTO> documentosExtension;
}
