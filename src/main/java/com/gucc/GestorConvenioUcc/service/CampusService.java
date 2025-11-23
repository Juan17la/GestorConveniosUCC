package com.gucc.GestorConvenioUcc.service;

import com.gucc.GestorConvenioUcc.dto.CampusDTO;
import com.gucc.GestorConvenioUcc.entity.Campus;
import com.gucc.GestorConvenioUcc.mapper.CampusMapper;
import com.gucc.GestorConvenioUcc.repository.CampusRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampusService {

    private final CampusRepository repository;
    private final CampusMapper mapper;

    public CampusDTO create(CampusDTO request){
        Campus newCampus = mapper.toEntity(request);
        Campus saved = repository.save(newCampus);
        return mapper.toDTO(saved);
    }

    // Obtener todos los campus
    public List<CampusDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    // Obtener campus por ID
    public CampusDTO getById(Long id) {
        Campus campus = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campus con ID " + id + " no encontrado"));
        return mapper.toDTO(campus);
    }

    // Actualizar campus
    public CampusDTO update(Long id, CampusDTO request) {
        Campus existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campus con ID " + id + " no encontrado"));
        
        existing.setNombre(request.getNombre());
        existing.setDireccion(request.getDireccion());
        
        Campus updated = repository.save(existing);
        return mapper.toDTO(updated);
    }

    // Eliminar campus
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Campus con ID " + id + " no encontrado");
        }
        repository.deleteById(id);
    }
}

