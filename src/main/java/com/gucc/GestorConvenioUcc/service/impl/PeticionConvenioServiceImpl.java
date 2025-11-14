package com.gucc.GestorConvenioUcc.service.impl;

import com.gucc.GestorConvenioUcc.dto.DocumentoConvenioDTO;
import com.gucc.GestorConvenioUcc.dto.PeticionConvenioDTO;
import com.gucc.GestorConvenioUcc.dto.RevisionAdministrativaDTO;
import com.gucc.GestorConvenioUcc.entity.PeticionConvenio;
import com.gucc.GestorConvenioUcc.entity.Usuario;
import com.gucc.GestorConvenioUcc.enums.EstadoPeticion;
import com.gucc.GestorConvenioUcc.mapper.DocumentoConvenioMapper;
import com.gucc.GestorConvenioUcc.mapper.PeticionConvenioMapper;
import com.gucc.GestorConvenioUcc.mapper.RevisionAdministrativaMapper;
import com.gucc.GestorConvenioUcc.repository.PeticionConvenioRepository;
import com.gucc.GestorConvenioUcc.service.DocumentoConvenioService;
import com.gucc.GestorConvenioUcc.service.PeticionConvenioService;
import com.gucc.GestorConvenioUcc.service.RevisionAdministrativaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PeticionConvenioServiceImpl implements PeticionConvenioService {

    private final PeticionConvenioMapper peticionConvenioMapper;
    private final PeticionConvenioRepository peticionConvenioRepository;
    private final DocumentoConvenioService documentoConvenioService;
    private final DocumentoConvenioMapper documentoConvenioMapper;
    private final RevisionAdministrativaService revisionAdministrativaService;
    private final RevisionAdministrativaMapper revisionAdministrativaMapper;

    @Override
    public PeticionConvenioDTO create(PeticionConvenioDTO request, List<MultipartFile> archivos) throws IOException, IOException {
        PeticionConvenio peticionConvenio = peticionConvenioMapper.toEntity(request);
        peticionConvenio.setFechaCreacion(new Date());

        // Guardar la petición sin documentos primero
        PeticionConvenio savedPeticion = peticionConvenioRepository.save(peticionConvenio);

        // Subir cada archivo al S3Service
        Usuario usuario = new Usuario(); // o el que tengas autenticado
        usuario.setId(request.getCreadorId());

        List<DocumentoConvenioDTO> documentosDTO = new ArrayList<>();

        for (MultipartFile archivo : archivos) {
            DocumentoConvenioDTO docDTO = documentoConvenioService.create(archivo, usuario);
            documentosDTO.add(docDTO);
        }

        // Asignar los documentos subidos
        savedPeticion.setDocumentos(
                documentosDTO.stream()
                        .map(documentoConvenioMapper::toEntity)
                        .collect(Collectors.toList())
        );

        // Guardar nuevamente con documentos
        PeticionConvenio updated = peticionConvenioRepository.save(savedPeticion);

        return peticionConvenioMapper.toDto(updated);
    }


    @Override
    public PeticionConvenioDTO findById(long id) {
        return null;
    }

    @Override
    public PeticionConvenioDTO setRevisionAdministrativa(long id, long idRevisor, Date fecha) {

        PeticionConvenio peticion = peticionConvenioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Petición no encontrada"));

//        Crea revisionAdministrativa;
        RevisionAdministrativaDTO revision = new RevisionAdministrativaDTO();
        revision.setRevisorId(idRevisor);
        revision.setFechaRevision(fecha);
        revision.setAprobada(true);
        revision.setRazonRechazo(null);

        RevisionAdministrativaDTO revisionCreada = revisionAdministrativaService.create(revision);

        // guardar revision en la peticion y cambiar estado
        peticion.setRevisionAdministrativa(revisionAdministrativaMapper.toEntity(revisionCreada));
        peticion.setEstado(EstadoPeticion.EN_REVISION_JURIDICA);

        PeticionConvenio updated = peticionConvenioRepository.save(peticion);

        return peticionConvenioMapper.toDto(updated);
    }

    @Override
    public List<PeticionConvenioDTO> findAll() {
        return List.of();
    }

    @Override
    public void delete(long id) {

    }
}
