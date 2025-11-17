package com.gucc.GestorConvenioUcc.service;

import com.gucc.GestorConvenioUcc.dto.RevisionJuridicaDTO;
import com.gucc.GestorConvenioUcc.entity.Peticion;
import com.gucc.GestorConvenioUcc.entity.RevisionJuridica;
import com.gucc.GestorConvenioUcc.entity.Usuario;
import com.gucc.GestorConvenioUcc.mapper.RevisionJuridicaMapper;
import com.gucc.GestorConvenioUcc.repository.PeticionRepository;
import com.gucc.GestorConvenioUcc.repository.RevisionJuridicaRepository;
import com.gucc.GestorConvenioUcc.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RevisionJuridicaService {

    private final RevisionJuridicaMapper mapperRevision;
    private final RevisionJuridicaRepository repositoryRevision;
    private final PeticionRepository repositoryPeticion;
    private final UsuarioRepository repositoryUsuario; // <- agregamos esto

    public RevisionJuridicaDTO create(RevisionJuridicaDTO request) {

        Peticion peticion = repositoryPeticion.findById(request.getPeticionId())
                .orElseThrow(() -> new RuntimeException("La peticion no existe"));

        boolean existeRevision = repositoryRevision.existsByPeticion(peticion);
        if (existeRevision) {
            throw new RuntimeException("Ya existe una revision para esta peticion");
        }

        Usuario revisor = repositoryUsuario.findById(request.getRevisorId())
                .orElseThrow(() -> new RuntimeException("Revisor no encontrado"));

        RevisionJuridica revision = mapperRevision.toEntity(request);
        revision.setPeticion(peticion);
        revision.setRevisor(revisor);

        RevisionJuridica saved = repositoryRevision.save(revision);
        return mapperRevision.toDTO(saved);
    }
}

