package com.gucc.GestorConvenioUcc.dto;

import com.gucc.GestorConvenioUcc.enums.EstadoAlerta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertaVencimientoDTO {
    private Long id;
    private LocalDateTime fechaGenerada;
    private Long convenioId;
    private String convenioNombre;
    private Long enviadaAId;
    private String enviadaANombre;
    private EstadoAlerta estado;
}
