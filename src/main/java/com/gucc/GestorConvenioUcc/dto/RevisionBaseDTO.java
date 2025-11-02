package com.gucc.GestorConvenioUcc.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public abstract class RevisionBaseDTO {
    private Long id;
    private LocalDateTime fechaRevision;
    private Long revisorId;
    private String revisorNombre;
    private Boolean aprobada;
    private String razonRechazo;
}
