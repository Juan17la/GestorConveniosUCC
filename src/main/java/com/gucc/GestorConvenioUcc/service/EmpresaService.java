package com.gucc.GestorConvenioUcc.service;

import com.gucc.GestorConvenioUcc.dto.EmpresaDTO;
import com.gucc.GestorConvenioUcc.entity.Empresa;
import com.gucc.GestorConvenioUcc.mapper.EmpresaMapper;
import com.gucc.GestorConvenioUcc.repository.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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


}
