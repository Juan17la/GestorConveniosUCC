package com.gucc.GestorConvenioUcc.service;

import com.gucc.GestorConvenioUcc.dto.ConvenioDTO;
import com.gucc.GestorConvenioUcc.entity.*;
import com.gucc.GestorConvenioUcc.enums.EstadoConvenio;
import com.gucc.GestorConvenioUcc.mapper.ConvenioMapper;
import com.gucc.GestorConvenioUcc.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConvenioService {

    private final ConvenioRepository repositoryConvenio;
    private final UsuarioRepository repositoryUsuario;
    private final CampusRepository repositoryCampus;
    private final EmpresaRepository repositoryEmpresa;
    private final PeticionRepository repositoryPeticion;
    private final ConvenioMapper mapper;

    @Transactional
    public ConvenioDTO create(ConvenioDTO request) {
        Convenio convenio = mapper.toEntity(request);
        Convenio saved = repositoryConvenio.save(convenio);
        return mapper.toDTO(saved);
    }

    public Convenio crearConvenioDesdePeticion(Peticion peticion) {
        return Convenio.builder()
                .nombreConvenio(peticion.getNombrePeticion())
                .fechaInicio(peticion.getFechaInicio())
                .fechaFinalizacion(peticion.getFechaFinalizacion())
                .tipo(peticion.getTipo())
                .creadoPor(peticion.getCreadoPor())
                .campus(peticion.getCampus())
                .empresa(peticion.getEmpresa())
                .peticionCreacion(peticion)
                .estado(EstadoConvenio.ACTIVO)
                .build();
    }
}
