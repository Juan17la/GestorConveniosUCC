package com.gucc.GestorConvenioUcc.service;

import com.gucc.GestorConvenioUcc.dto.PeticionConvenioDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PeticionConvenioService {
    PeticionConvenioDTO create(PeticionConvenioDTO request, List<MultipartFile> archivos) throws IOException;
    PeticionConvenioDTO findById(long id);
    List<PeticionConvenioDTO> findAll();
    void delete(long id);
}
