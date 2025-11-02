package com.gucc.GestorConvenioUcc.dto;

import com.gucc.GestorConvenioUcc.enums.EstadoPeticion;
import com.gucc.GestorConvenioUcc.enums.TipoConvenio;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PeticionConvenioDTO {
    private Long id;
    private LocalDateTime fechaCreacion;
    private TipoConvenio tipo;
    private EstadoPeticion estado;
    private Long creadorId;
    private String creadorNombre;
    private Long campusId;
    private String campusNombre;
    private RevisionAdministrativaDTO revisionAdministrativa;
    private RevisionJuridicaDTO revisionJuridica;
    private FirmaConvenioDTO firmaConvenio;
    private List<DocumentoConvenioDTO> documentos;
}
