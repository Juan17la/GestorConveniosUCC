package com.gucc.GestorConvenioUcc.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class FirmaConvenioDTO {
    private Long id;
    private Date fechaFirma;
    private Long directivoFirmanteId;
    private Boolean firmado;
    private String razonRechazo;
}
