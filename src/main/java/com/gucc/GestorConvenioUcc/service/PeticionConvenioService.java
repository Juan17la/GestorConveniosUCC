package com.gucc.GestorConvenioUcc.service;

import com.gucc.GestorConvenioUcc.dto.PeticionConvenioDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface PeticionConvenioService {
    PeticionConvenioDTO create(PeticionConvenioDTO request, List<MultipartFile> archivos) throws IOException;

    PeticionConvenioDTO findById(long id);

    PeticionConvenioDTO setRevisionAdministrativa(long id, long idRevisor, Date fecha);

    List<PeticionConvenioDTO> findAll();

    void delete(long id);
}
