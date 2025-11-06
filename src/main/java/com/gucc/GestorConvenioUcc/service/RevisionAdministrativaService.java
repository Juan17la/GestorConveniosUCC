package com.gucc.GestorConvenioUcc.service;

import com.gucc.GestorConvenioUcc.dto.RevisionAdministrativaDTO;

import java.util.List;

public interface RevisionAdministrativaService {
    RevisionAdministrativaDTO create(RevisionAdministrativaDTO request);

    RevisionAdministrativaDTO findById(long id);

    List<RevisionAdministrativaDTO> findAll();

    void delete(long id);
}
