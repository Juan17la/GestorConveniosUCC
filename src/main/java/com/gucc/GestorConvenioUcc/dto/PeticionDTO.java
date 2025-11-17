package com.gucc.GestorConvenioUcc.dto;

import com.gucc.GestorConvenioUcc.enums.TipoConvenio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PeticionDTO {
    private Long id;
    private String nombrePeticion;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFinalizacion;
    private TipoConvenio tipo;
    private Long creadoPorId;
    private Long campusId;
    private Long empresaId;
    private Long revisionJuridicaId;
    private Long firmaConvenioCampusId;
    private Long firmaConvenioNacionalId;
    private List<Long> documentoIds;
    private Long convenioId;
    private LocalDateTime fechaCreacion;
}
