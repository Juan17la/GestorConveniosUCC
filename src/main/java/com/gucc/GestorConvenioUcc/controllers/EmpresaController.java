package com.gucc.GestorConvenioUcc.controllers;

import com.gucc.GestorConvenioUcc.dto.EmpresaDTO;
import com.gucc.GestorConvenioUcc.service.EmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresa")
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaService service;

    // Crear nueva empresa
    @PostMapping
    public ResponseEntity<EmpresaDTO> create(@RequestBody EmpresaDTO request) {
        return ResponseEntity.ok(service.create(request));
    }

    // Obtener todas las empresas
    @GetMapping
    public ResponseEntity<List<EmpresaDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // Obtener empresa por ID
    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // Actualizar empresa
    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDTO> update(
            @PathVariable Long id,
            @RequestBody EmpresaDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    // Eliminar empresa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
