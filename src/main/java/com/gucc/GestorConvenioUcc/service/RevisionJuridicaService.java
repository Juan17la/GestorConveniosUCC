package com.gucc.GestorConvenioUcc.service;

import com.gucc.GestorConvenioUcc.dto.RevisionJuridicaDTO;

import java.util.List;

public interface RevisionJuridicaService {
    RevisionJuridicaDTO create(RevisionJuridicaDTO request);

    RevisionJuridicaDTO findById(long id);

    List<RevisionJuridicaDTO> findAll();

    void delete(long id);
}
