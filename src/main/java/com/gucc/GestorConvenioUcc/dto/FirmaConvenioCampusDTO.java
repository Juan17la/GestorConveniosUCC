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
public class FirmaConvenioCampusDTO {
    private Long id;
    private LocalDateTime fechaCreacion;
    private Long revisorId;
    private Long peticionId;
    private Long documentoFirmadoId;
    private Boolean aprobada;
    private String mensajeRechazo;
}
