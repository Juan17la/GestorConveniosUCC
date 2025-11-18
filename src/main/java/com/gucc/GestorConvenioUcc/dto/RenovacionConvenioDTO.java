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
public class RenovacionConvenioDTO {
    private Long id;
    private Long convenioOriginalId;
    private Long nuevaPeticionId;
    private LocalDateTime fechaCreacion;
}
