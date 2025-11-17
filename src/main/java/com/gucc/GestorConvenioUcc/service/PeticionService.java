package com.gucc.GestorConvenioUcc.service;

import com.gucc.GestorConvenioUcc.dto.DocumentoDTO;
import com.gucc.GestorConvenioUcc.dto.EmpresaDTO;
import com.gucc.GestorConvenioUcc.dto.PeticionDTO;
import com.gucc.GestorConvenioUcc.entity.Documento;
import com.gucc.GestorConvenioUcc.entity.Empresa;
import com.gucc.GestorConvenioUcc.entity.Peticion;
import com.gucc.GestorConvenioUcc.entity.Usuario;
import com.gucc.GestorConvenioUcc.mapper.EmpresaMapper;
import com.gucc.GestorConvenioUcc.mapper.PeticionMapper;
import com.gucc.GestorConvenioUcc.repository.EmpresaRepository;
import com.gucc.GestorConvenioUcc.repository.PeticionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PeticionService {

    private final PeticionMapper mapper;
    private final PeticionRepository repository;
    private final EmpresaService serviceEmpresa;
    private final DocumentoService serviceDocumento;

    public PeticionDTO create(PeticionDTO requestPeticion, EmpresaDTO requestEmpresa, MultipartFile minuta) throws IOException {

        // 1. Crear o recuperar empresa
        EmpresaDTO empresaDTO = serviceEmpresa.create(requestEmpresa);

        // 2. Convertir PeticionDTO a entidad
        Peticion peticion = mapper.toEntity(requestPeticion);

        // *** ASIGNAR LA EMPRESA A LA PETICIÓN ***
        peticion.setEmpresa(Empresa.builder()
                .id(empresaDTO.getId())
                .nit(empresaDTO.getNit())
                .nombre(empresaDTO.getNombre())
                .direccion(empresaDTO.getDireccion())
                .telefono(empresaDTO.getTelefono())
                .representante(empresaDTO.getRepresentante())
                .build()
        );

        // 3. Guardar la petición primero
        Peticion saved = repository.save(peticion);

        // 4. Subir documento
        Usuario usuario = new Usuario();
        usuario.setId(saved.getCreadoPor().getId());

        Documento minutaEntity = serviceDocumento.create(minuta, usuario);

        // 5. Relación bidireccional
        saved.getDocumentos().add(minutaEntity);
        minutaEntity.setPeticion(saved);

        return mapper.toDTO(saved);
    }

}
