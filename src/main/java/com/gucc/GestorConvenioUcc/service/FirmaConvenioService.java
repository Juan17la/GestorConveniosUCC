package com.gucc.GestorConvenioUcc.service;

import com.gucc.GestorConvenioUcc.dto.FirmaConvenioDTO;
import com.gucc.GestorConvenioUcc.dto.RevisionJuridicaDTO;

import java.util.List;

public interface FirmaConvenioService {
    FirmaConvenioDTO create(FirmaConvenioDTO request);

    FirmaConvenioDTO findById(long id);

    List<FirmaConvenioDTO> findAll();

    void delete(long id);
}
