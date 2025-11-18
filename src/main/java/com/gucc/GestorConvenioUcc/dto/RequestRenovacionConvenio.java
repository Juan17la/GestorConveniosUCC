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
public class RequestRenovacionConvenio {
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFinalizacion;
    private Long creadoPor;
}
