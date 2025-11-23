package com.gucc.GestorConvenioUcc.service;

import com.gucc.GestorConvenioUcc.dto.DocumentoDTO;
import com.gucc.GestorConvenioUcc.entity.Documento;
import com.gucc.GestorConvenioUcc.entity.Peticion;
import com.gucc.GestorConvenioUcc.entity.Usuario;
import com.gucc.GestorConvenioUcc.mapper.DocumentoMapper;
import com.gucc.GestorConvenioUcc.repository.DocumentoRepository;
import com.gucc.GestorConvenioUcc.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentoService {

    private final DocumentoRepository repository;
    private final DocumentoMapper mapper;
    private final S3Service serviceS3;
    private final UsuarioRepository repositoryUsuario;

    public Documento create(MultipartFile file, Usuario usuario) throws IOException {

        String fileUrl = serviceS3.upload(file);

        Documento documento = new Documento();
        documento.setNombreOriginal(file.getOriginalFilename());
        documento.setUrl(fileUrl);
        documento.setTipoArchivo(file.getContentType());
        documento.setSubidoPor(usuario);
        documento.setPeticion(null);
        documento.setConvenio(null);

        return repository.save(documento);
    }

    public DocumentoDTO createDTO(MultipartFile file, Usuario usuario) throws IOException {

        String fileUrl = serviceS3.upload(file);

        Documento documento = new Documento();
        documento.setNombreOriginal(file.getOriginalFilename());
        documento.setUrl(fileUrl);
        documento.setTipoArchivo(file.getContentType());
        documento.setSubidoPor(usuario);
        documento.setPeticion(null);
        documento.setConvenio(null);

        Documento saved = repository.save(documento);
        return mapper.toDTO(saved);
    }

    // Obtener todos los documentos
    public List<DocumentoDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    // Obtener documento por ID
    public DocumentoDTO getById(Long id) {
        Documento documento = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Documento con ID " + id + " no encontrado"));
        return mapper.toDTO(documento);
    }

    // Eliminar documento
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Documento con ID " + id + " no encontrado");
        }
        repository.deleteById(id);
    }

}
