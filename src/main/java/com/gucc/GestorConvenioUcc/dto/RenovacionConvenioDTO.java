package com.gucc.GestorConvenioUcc.dto;

import com.gucc.GestorConvenioUcc.enums.TipoRenovacion;
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
    private String convenioOriginalNombre;
    private Long nuevaPeticionId;
    private LocalDateTime fecha;
    private TipoRenovacion accion;
}
