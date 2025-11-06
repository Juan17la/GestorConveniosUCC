package com.gucc.GestorConvenioUcc.service;

import com.gucc.GestorConvenioUcc.dto.CampusDTO;

import java.util.List;

public interface CampusService {
    CampusDTO create(CampusDTO request);

    CampusDTO findById(Long id);

    List<CampusDTO> findAll();

    void delete(Long id);
}
