package com.gucc.GestorConvenioUcc.service;

import com.gucc.GestorConvenioUcc.dto.UsuarioDTO;
import com.gucc.GestorConvenioUcc.entity.Usuario;
import com.gucc.GestorConvenioUcc.mapper.UsuarioMapper;
import com.gucc.GestorConvenioUcc.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;


    public UsuarioDTO create(UsuarioDTO request){
        Usuario newUsuario = mapper.toEntity(request);
        Usuario saved = repository.save(newUsuario);
        return mapper.toDTO(saved);
    }

}
