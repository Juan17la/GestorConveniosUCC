package com.gucc.GestorConvenioUcc.service;

import com.gucc.GestorConvenioUcc.dto.DocumentoConvenioDTO;
import com.gucc.GestorConvenioUcc.entity.DocumentoConvenio;
import com.gucc.GestorConvenioUcc.entity.Usuario;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DocumentoConvenioService {

    DocumentoConvenioDTO create(MultipartFile archivo, Usuario usuario) throws IOException;

}
