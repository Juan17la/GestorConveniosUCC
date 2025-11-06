package com.gucc.GestorConvenioUcc.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public abstract class RevisionBaseDTO {
    private Long id;
    private Date fechaRevision;
    private Long revisorId;
    private String revisorNombre;
    private Boolean aprobada;
    private String razonRechazo;
}
