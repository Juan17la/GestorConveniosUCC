package com.gucc.GestorConvenioUcc.controllers;

import com.gucc.GestorConvenioUcc.dto.DocumentoDTO;
import com.gucc.GestorConvenioUcc.entity.Documento;
import com.gucc.GestorConvenioUcc.entity.Usuario;
import com.gucc.GestorConvenioUcc.service.DocumentoService;
import com.gucc.GestorConvenioUcc.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/documento")
@RequiredArgsConstructor
public class DocumentoController {

    private final DocumentoService service;

    @PostMapping(value = "create")
    public ResponseEntity<DocumentoDTO> create(
            @RequestParam("file") MultipartFile file,
            @RequestParam("usuario_id") Long usuario_id
    ) throws IOException {
        Usuario usuario = new Usuario();
        usuario.setId(usuario_id);
        return ResponseEntity.ok(service.createDTO(file, usuario));
    }
}
