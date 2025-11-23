package com.gucc.GestorConvenioUcc.controllers;

import com.gucc.GestorConvenioUcc.dto.CampusDTO;
import com.gucc.GestorConvenioUcc.service.CampusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campus")
@RequiredArgsConstructor
public class CampusController {

    private final CampusService service;

    // Crear nuevo campus
    @PostMapping
    public ResponseEntity<CampusDTO> create(@RequestBody CampusDTO request){
        return ResponseEntity.ok(service.create(request));
    }

    // Obtener todos los campus
    @GetMapping
    public ResponseEntity<List<CampusDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // Obtener campus por ID
    @GetMapping("/{id}")
    public ResponseEntity<CampusDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // Actualizar campus
    @PutMapping("/{id}")
    public ResponseEntity<CampusDTO> update(
            @PathVariable Long id,
            @RequestBody CampusDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    // Eliminar campus
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
