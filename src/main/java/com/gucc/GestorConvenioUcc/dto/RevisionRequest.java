package com.gucc.GestorConvenioUcc.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RevisionRequest {
    private Long idRevisor;
    private Date fechaRevision;
}

