package com.gucc.GestorConvenioUcc.service;

import com.gucc.GestorConvenioUcc.dto.AlertaVencimientoDTO;
import com.gucc.GestorConvenioUcc.entity.AlertaVencimiento;
import com.gucc.GestorConvenioUcc.mapper.AlertaVencimientoMapper;
import com.gucc.GestorConvenioUcc.repository.AlertaVencimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertaVencimientoService {

    private final AlertaVencimientoRepository repository;
    private final AlertaVencimientoMapper mapper;

    // Obtener todas las alertas
    public List<AlertaVencimientoDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    // Obtener alerta por ID
    public AlertaVencimientoDTO getById(Long id) {
        AlertaVencimiento alerta = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alerta con ID " + id + " no encontrada"));
        return mapper.toDTO(alerta);
    }

    // Obtener alertas por estado
    public List<AlertaVencimientoDTO> getByEstado(String estado) {
        return repository.findByEstado(com.gucc.GestorConvenioUcc.enums.EstadoAlerta.valueOf(estado))
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    // Actualizar estado de alerta
    public AlertaVencimientoDTO updateEstado(Long id, AlertaVencimientoDTO request) {
        AlertaVencimiento existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alerta con ID " + id + " no encontrada"));
        
        existing.setEstado(request.getEstado());
        
        AlertaVencimiento updated = repository.save(existing);
        return mapper.toDTO(updated);
    }
}
