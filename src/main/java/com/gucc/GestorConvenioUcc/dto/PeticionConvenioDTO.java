package com.gucc.GestorConvenioUcc.dto;

import com.gucc.GestorConvenioUcc.enums.EstadoPeticion;
import com.gucc.GestorConvenioUcc.enums.TipoConvenio;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class PeticionConvenioDTO {
    private Long id;
    private Date fechaCreacion;
    private TipoConvenio tipo;
    private EstadoPeticion estado;
    private Long creadorId;
    private Long campusId;
    private RevisionAdministrativaDTO revisionAdministrativa;
    private RevisionJuridicaDTO revisionJuridica;
    private FirmaConvenioDTO firmaConvenio;
    private List<DocumentoConvenioDTO> documentos;
}
