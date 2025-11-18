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

    // IDs para las relaciones
    private Long empresaId;
    private Long convenioId;

    // Información adicional para mostrar
    private String empresaNombre;
    private String convenioNombre;
}
