package com.gucc.GestorConvenioUcc.controllers;

import com.gucc.GestorConvenioUcc.dto.AlertaVencimientoDTO;
import com.gucc.GestorConvenioUcc.service.AlertaVencimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerta-vencimiento")
@RequiredArgsConstructor
public class AlertaVencimientoController {

    private final AlertaVencimientoService service;

    // Obtener todas las alertas
    @GetMapping
    public ResponseEntity<List<AlertaVencimientoDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // Obtener alerta por ID
    @GetMapping("/{id}")
    public ResponseEntity<AlertaVencimientoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // Obtener alertas por estado
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<AlertaVencimientoDTO>> getByEstado(@PathVariable String estado) {
        return ResponseEntity.ok(service.getByEstado(estado));
    }

    // Actualizar estado de alerta
    @PutMapping("/{id}/estado")
    public ResponseEntity<AlertaVencimientoDTO> updateEstado(
            @PathVariable Long id,
            @RequestBody AlertaVencimientoDTO request) {
        return ResponseEntity.ok(service.updateEstado(id, request));
    }
}
