package com.gucc.GestorConvenioUcc.service.impl;

import com.gucc.GestorConvenioUcc.config.S3Config;
import com.gucc.GestorConvenioUcc.dto.DocumentoConvenioDTO;
import com.gucc.GestorConvenioUcc.entity.DocumentoConvenio;
import com.gucc.GestorConvenioUcc.entity.Usuario;
import com.gucc.GestorConvenioUcc.mapper.DocumentoConvenioMapper;
import com.gucc.GestorConvenioUcc.repository.DocumentoConvenioRepository;
import com.gucc.GestorConvenioUcc.service.DocumentoConvenioService;
import com.gucc.GestorConvenioUcc.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class DocumentoConvenioServiceImpl implements DocumentoConvenioService {

    private final S3Service s3Service;
    private final DocumentoConvenioMapper documentoConvenioMapper;
    private final DocumentoConvenioRepository documentoConvenioRepository;

    @Override
    public DocumentoConvenioDTO create(MultipartFile archivo, Usuario usuario) throws IOException {
        String urlFile = s3Service.upload(archivo);

        DocumentoConvenio documentoConvenio = new DocumentoConvenio();
        documentoConvenio.setNombreArchivo(archivo.getOriginalFilename());
        documentoConvenio.setUrl(urlFile);
        documentoConvenio.setTipoArchivo(archivo.getContentType());
        documentoConvenio.setSubidoPor(usuario);
        documentoConvenio.setFechaSubida(new Date());

        DocumentoConvenio saved = documentoConvenioRepository.save(documentoConvenio);

        return documentoConvenioMapper.toDto(saved);
    }

}
