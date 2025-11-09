package com.gucc.GestorConvenioUcc.controllers;

import com.gucc.GestorConvenioUcc.dto.CampusDTO;
import com.gucc.GestorConvenioUcc.dto.DocumentoConvenioDTO;
import com.gucc.GestorConvenioUcc.entity.DocumentoConvenio;
import com.gucc.GestorConvenioUcc.entity.Usuario;
import com.gucc.GestorConvenioUcc.service.DocumentoConvenioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/documento")
@RequiredArgsConstructor
public class DocumentoConvenioController {

    private final DocumentoConvenioService documentoConvenioService;

    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public ResponseEntity<DocumentoConvenioDTO> create(
            @RequestParam("file") MultipartFile archivo,
            @RequestParam("usuarioId") Long usuarioId
    ) throws IOException {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        return ResponseEntity.ok(documentoConvenioService.create(archivo, usuario));
    }

}
