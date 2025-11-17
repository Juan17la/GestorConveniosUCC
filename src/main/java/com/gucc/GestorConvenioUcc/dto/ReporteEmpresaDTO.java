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
public class ReporteEmpresaDTO {
    private Long id;
    private LocalDateTime fechaReporte;
    private String descripcion;
    private String actividades;
    private String asistencia;
    private Long empresaId;
    private String empresaNombre;
    private Long convenioId;
    private String convenioNombre;
}
