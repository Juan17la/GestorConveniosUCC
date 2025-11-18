package com.gucc.GestorConvenioUcc.service;

import com.gucc.GestorConvenioUcc.dto.FirmaConvenioCampusDTO;
import com.gucc.GestorConvenioUcc.entity.Documento;
import com.gucc.GestorConvenioUcc.entity.FirmaConvenioCampus;
import com.gucc.GestorConvenioUcc.entity.Peticion;
import com.gucc.GestorConvenioUcc.entity.Usuario;
import com.gucc.GestorConvenioUcc.enums.EstadoPeticion;
import com.gucc.GestorConvenioUcc.mapper.FirmaConvenioCampusMapper;
import com.gucc.GestorConvenioUcc.repository.FirmaConvenioCampusRepository;
import com.gucc.GestorConvenioUcc.repository.PeticionRepository;
import com.gucc.GestorConvenioUcc.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FirmaConvenioCampusService {

    private final FirmaConvenioCampusMapper mapperFirma;
    private final FirmaConvenioCampusRepository repositoryFirma;
    private final PeticionRepository repositoryPeticion;
    private final UsuarioRepository repositoryUsuario;
    private final DocumentoService serviceDocumento;

    @Transactional
    public FirmaConvenioCampusDTO create(FirmaConvenioCampusDTO request, MultipartFile firma) throws IOException {

        // Validar que la petición existe
        Peticion peticion = repositoryPeticion.findById(request.getPeticionId())
                .orElseThrow(() -> new RuntimeException("La petición no existe"));

        // Validar que no existe una firma nacional previa
        if (repositoryFirma.existsByPeticion(peticion)) {
            throw new RuntimeException("Ya existe una firma nacional para esta petición");
        }

        // Validar que el estado de la petición es correcto
        if (peticion.getEstado() != EstadoPeticion.EN_FIRMA_CAMPUS) {
            throw new RuntimeException("La petición no está en estado de firma nacional");
        }

        Usuario revisor = repositoryUsuario.findById(request.getRevisorId())
                .orElseThrow(() -> new RuntimeException("Revisor no encontrado"));

        Documento firmaDocumento = serviceDocumento.create(firma, revisor);
        firmaDocumento.setPeticion(peticion);

        peticion.setEstado(EstadoPeticion.EN_FIRMA_NACIONAL);

        FirmaConvenioCampus firmaCampus = mapperFirma.toEntity(request);
        firmaCampus.setPeticion(peticion);
        firmaCampus.setRevisor(revisor);
        firmaCampus.setDocumentoFirmado(firmaDocumento);

        FirmaConvenioCampus saved = repositoryFirma.save(firmaCampus);

        return mapperFirma.toDTO(saved);
    }

}
