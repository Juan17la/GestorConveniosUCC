package com.gucc.GestorConvenioUcc.service;

import com.gucc.GestorConvenioUcc.dto.RevisionJuridicaDTO;
import com.gucc.GestorConvenioUcc.entity.Peticion;
import com.gucc.GestorConvenioUcc.entity.RevisionJuridica;
import com.gucc.GestorConvenioUcc.entity.Usuario;
import com.gucc.GestorConvenioUcc.enums.EstadoPeticion;
import com.gucc.GestorConvenioUcc.mapper.RevisionJuridicaMapper;
import com.gucc.GestorConvenioUcc.repository.PeticionRepository;
import com.gucc.GestorConvenioUcc.repository.RevisionJuridicaRepository;
import com.gucc.GestorConvenioUcc.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RevisionJuridicaService {

    private final RevisionJuridicaMapper mapperRevision;
    private final RevisionJuridicaRepository repositoryRevision;
    private final PeticionRepository repositoryPeticion;
    private final UsuarioRepository repositoryUsuario;

    @Transactional
    public RevisionJuridicaDTO create(RevisionJuridicaDTO request) {

        // Validar que la petición existe
        Peticion peticion = repositoryPeticion.findById(request.getPeticionId())
                .orElseThrow(() -> new RuntimeException("La petición no existe"));

        // Validar que no existe una revisión jurídica previa
        if (repositoryRevision.existsByPeticion(peticion)) {
            throw new RuntimeException("Ya existe una revisión jurídica para esta petición");
        }

        // Validar que el estado de la petición es correcto
        if (peticion.getEstado() != EstadoPeticion.EN_REVISION_JURIDICA) {
            throw new RuntimeException("La petición no está en estado de revisión jurídica");
        }

        // Obtener el revisor
        Usuario revisor = repositoryUsuario.findById(request.getRevisorId())
                .orElseThrow(() -> new RuntimeException("Revisor no encontrado"));

        // Cambiar estado de la petición a EN_FIRMA_CAMPUS
        peticion.setEstado(EstadoPeticion.EN_FIRMA_CAMPUS);

        // Crear la revisión jurídica
        RevisionJuridica revision = mapperRevision.toEntity(request);
        revision.setPeticion(peticion);
        revision.setRevisor(revisor);

        // Guardar la revisión jurídica
        RevisionJuridica saved = repositoryRevision.save(revision);

        return mapperRevision.toDTO(saved);
    }

}

