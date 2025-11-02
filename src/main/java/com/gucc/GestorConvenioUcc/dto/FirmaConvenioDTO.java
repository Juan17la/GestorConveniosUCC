package com.gucc.GestorConvenioUcc.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class FirmaConvenioDTO {
    private Long id;
    private LocalDateTime fechaFirma;
    private Long directivoFirmanteId;
    private String directivoFirmanteNombre;
    private Boolean firmado;
    private String razonRechazo;
}
