package com.gucc.GestorConvenioUcc.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public abstract class RevisionBaseDTO {
    private Long id;
    private Long revisorId;
    private Date fechaRevision;
    private Boolean aprobada;
    private String razonRechazo;
}
