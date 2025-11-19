package com.gucc.GestorConvenioUcc.service;

import com.gucc.GestorConvenioUcc.dto.UsuarioDTO;
import com.gucc.GestorConvenioUcc.entity.Campus;
import com.gucc.GestorConvenioUcc.entity.Usuario;
import com.gucc.GestorConvenioUcc.mapper.UsuarioMapper;
import com.gucc.GestorConvenioUcc.repository.CampusRepository;
import com.gucc.GestorConvenioUcc.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
            newUsuario.setCampus(null); // o lanza error
        }

        Usuario saved = repository.save(newUsuario);
        return mapper.toDTO(saved);
    }

}
