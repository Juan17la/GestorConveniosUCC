package com.gucc.GestorConvenioUcc.dto;

import lombok.Data;
import java.util.List;

@Data
public class HistorialConvenioDTO {
    private Long id;
    private Long convenioId;
    private List<DocumentoConvenioDTO> historialDocumentos;
}
