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
import java.util.List;

@RestController
@RequestMapping("/api/documento")
@RequiredArgsConstructor
public class DocumentoController {

    private final DocumentoService service;

    // Crear nuevo documento
    @PostMapping
    public ResponseEntity<DocumentoDTO> create(
            @RequestParam("file") MultipartFile file,
            @RequestParam("usuario_id") Long usuario_id
    ) throws IOException {
        Usuario usuario = new Usuario();
        usuario.setId(usuario_id);
        return ResponseEntity.ok(service.createDTO(file, usuario));
    }

    // Obtener todos los documentos
    @GetMapping
    public ResponseEntity<List<DocumentoDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // Obtener documento por ID
    @GetMapping("/{id}")
    public ResponseEntity<DocumentoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // Eliminar documento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
