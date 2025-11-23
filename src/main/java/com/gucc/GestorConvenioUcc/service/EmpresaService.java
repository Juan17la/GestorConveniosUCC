package com.gucc.GestorConvenioUcc.service;

import com.gucc.GestorConvenioUcc.dto.EmpresaDTO;
import com.gucc.GestorConvenioUcc.entity.Empresa;
import com.gucc.GestorConvenioUcc.mapper.EmpresaMapper;
import com.gucc.GestorConvenioUcc.repository.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpresaService {

    private final EmpresaMapper mapper;
    private final EmpresaRepository repository;

    public EmpresaDTO create(EmpresaDTO request) {

        Empresa empresa = mapper.toEntity(request);

        Optional<Empresa> existe = repository.findByNit(empresa.getNit());

        if (existe.isPresent()) {
            return mapper.toDTO(existe.get());
        }

        Empresa saved = repository.save(empresa);
        return mapper.toDTO(saved);
    }

    // Obtener todas las empresas
    public List<EmpresaDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    // Obtener empresa por ID
    public EmpresaDTO getById(Long id) {
        Empresa empresa = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa con ID " + id + " no encontrada"));
        return mapper.toDTO(empresa);
    }

    // Actualizar empresa
    public EmpresaDTO update(Long id, EmpresaDTO request) {
        Empresa existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa con ID " + id + " no encontrada"));
        
        existing.setNombre(request.getNombre());
        existing.setNit(request.getNit());
        existing.setDireccion(request.getDireccion());
        existing.setTelefono(request.getTelefono());
        existing.setRepresentante(request.getRepresentante());
        
        Empresa updated = repository.save(existing);
        return mapper.toDTO(updated);
    }

    // Eliminar empresa
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Empresa con ID " + id + " no encontrada");
        }
        repository.deleteById(id);
    }

}
