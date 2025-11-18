package com.gucc.GestorConvenioUcc.service;

import com.gucc.GestorConvenioUcc.dto.ConvenioDTO;
import com.gucc.GestorConvenioUcc.dto.FirmaConvenioNacionalDTO;
import com.gucc.GestorConvenioUcc.entity.*;
import com.gucc.GestorConvenioUcc.enums.EstadoPeticion;
import com.gucc.GestorConvenioUcc.mapper.ConvenioMapper;
import com.gucc.GestorConvenioUcc.mapper.FirmaConvenioNacionalMapper;
import com.gucc.GestorConvenioUcc.repository.ConvenioRepository;
import com.gucc.GestorConvenioUcc.repository.FirmaConvenioNacionalRepository;
import com.gucc.GestorConvenioUcc.repository.PeticionRepository;
import com.gucc.GestorConvenioUcc.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FirmaConvenioNacionalService {

    private final FirmaConvenioNacionalMapper mapperFirma;
    private final FirmaConvenioNacionalRepository repositoryFirma;
    private final PeticionRepository repositoryPeticion;
    private final UsuarioRepository repositoryUsuario;
    private final ConvenioRepository repositoryConvenio;
    private final ConvenioService convenioService;
    private final DocumentoService serviceDocumento;

    @Transactional
    public FirmaConvenioNacionalDTO create(FirmaConvenioNacionalDTO request, MultipartFile firma) throws IOException {

        // Validar que la petición existe
        Peticion peticion = repositoryPeticion.findById(request.getPeticionId())
                .orElseThrow(() -> new RuntimeException("La petición no existe"));

        // Validar que no existe una firma nacional previa
        if (repositoryFirma.existsByPeticion(peticion)) {
            throw new RuntimeException("Ya existe una firma nacional para esta petición");
        }

        // Validar que el estado de la petición es correcto
        if (peticion.getEstado() != EstadoPeticion.EN_FIRMA_NACIONAL) {
            throw new RuntimeException("La petición no está en estado de firma nacional");
        }

        // Obtener el revisor
        Usuario revisor = repositoryUsuario.findById(request.getRevisorId())
                .orElseThrow(() -> new RuntimeException("Revisor no encontrado"));

        // Crear y guardar el documento de firma
        Documento firmaDocumento = serviceDocumento.create(firma, revisor);
        firmaDocumento.setPeticion(peticion);

        // Cambiar estado de la petición a APROBADA
        peticion.setEstado(EstadoPeticion.APROBADA);

        // Crear la firma nacional
        FirmaConvenioNacional firmaNacional = mapperFirma.toEntity(request);
        firmaNacional.setPeticion(peticion);
        firmaNacional.setRevisor(revisor);
        firmaNacional.setDocumentoFirmado(firmaDocumento);
        firmaNacional.setAprobada(true);

        // Guardar la firma nacional
        FirmaConvenioNacional savedFirma = repositoryFirma.save(firmaNacional);

        // Crear el convenio a partir de la petición
        Convenio convenio = convenioService.crearConvenioDesdePeticion(peticion);
        repositoryConvenio.save(convenio);

        return mapperFirma.toDTO(savedFirma);
    }
}
