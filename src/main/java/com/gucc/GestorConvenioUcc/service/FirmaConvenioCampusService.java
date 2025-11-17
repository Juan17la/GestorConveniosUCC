package com.gucc.GestorConvenioUcc.service;

import com.gucc.GestorConvenioUcc.dto.FirmaConvenioCampusDTO;
import com.gucc.GestorConvenioUcc.entity.Documento;
import com.gucc.GestorConvenioUcc.entity.FirmaConvenioCampus;
import com.gucc.GestorConvenioUcc.entity.Peticion;
import com.gucc.GestorConvenioUcc.entity.Usuario;
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

        Peticion peticion = repositoryPeticion.findById(request.getPeticionId())
                .orElseThrow(() -> new RuntimeException("La petición no existe"));

        if (repositoryFirma.existsByPeticion(peticion)) {
            throw new RuntimeException("Ya existe una firma campus para esta petición");
        }

        Usuario revisor = repositoryUsuario.findById(request.getRevisorId())
                .orElseThrow(() -> new RuntimeException("Revisor no encontrado"));

        Documento firmaDocumento = serviceDocumento.create(firma, revisor);
        firmaDocumento.setPeticion(peticion); // relacionar con la petición

        FirmaConvenioCampus firmaCampus = mapperFirma.toEntity(request);
        firmaCampus.setPeticion(peticion);
        firmaCampus.setRevisor(revisor);
        firmaCampus.setDocumentoFirmado(firmaDocumento); // asignar documento ya persistido

        FirmaConvenioCampus saved = repositoryFirma.save(firmaCampus);

        return mapperFirma.toDTO(saved);
    }

}
