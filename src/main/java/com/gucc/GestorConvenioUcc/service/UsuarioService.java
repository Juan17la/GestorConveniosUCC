package com.gucc.GestorConvenioUcc.service;

import com.gucc.GestorConvenioUcc.dto.UsuarioDTO;
import com.gucc.GestorConvenioUcc.entity.Campus;
import com.gucc.GestorConvenioUcc.entity.Usuario;
import com.gucc.GestorConvenioUcc.mapper.UsuarioMapper;
import com.gucc.GestorConvenioUcc.repository.CampusRepository;
import com.gucc.GestorConvenioUcc.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;
    private final CampusRepository campusRepository;


    public UsuarioDTO create(UsuarioDTO request){
        Usuario newUsuario = mapper.toEntity(request);

        if(request.getCampusId() != null){
            Campus campus = campusRepository.findById(request.getCampusId())
                    .orElseThrow(() -> new RuntimeException("Campus no existe"));
            newUsuario.setCampus(campus);
        } else {
            newUsuario.setCampus(null);
        }

        Usuario saved = repository.save(newUsuario);
        return mapper.toDTO(saved);
    }

    // Obtener todos los usuarios
    public List<UsuarioDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    // Obtener usuario por ID
    public UsuarioDTO getById(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario con ID " + id + " no encontrado"));
        return mapper.toDTO(usuario);
    }

    // Actualizar usuario
    public UsuarioDTO update(Long id, UsuarioDTO request) {
        Usuario existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario con ID " + id + " no encontrado"));
        
        existing.setNombre(request.getNombre());
        existing.setApellidos(request.getApellidos());
        existing.setEmail(request.getEmail());
        existing.setRol(request.getRol());
        
        if(request.getCampusId() != null){
            Campus campus = campusRepository.findById(request.getCampusId())
                    .orElseThrow(() -> new RuntimeException("Campus no existe"));
            existing.setCampus(campus);
        }
        
        Usuario updated = repository.save(existing);
        return mapper.toDTO(updated);
    }

    // Eliminar usuario
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Usuario con ID " + id + " no encontrado");
        }
        repository.deleteById(id);
    }

}
