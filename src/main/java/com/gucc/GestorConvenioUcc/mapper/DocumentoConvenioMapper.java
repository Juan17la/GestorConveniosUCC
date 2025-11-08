package com.gucc.GestorConvenioUcc.mapper;

import com.gucc.GestorConvenioUcc.dto.DocumentoConvenioDTO;
import com.gucc.GestorConvenioUcc.entity.DocumentoConvenio;
import com.gucc.GestorConvenioUcc.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DocumentoConvenioMapper implements BaseMapper<DocumentoConvenioDTO, DocumentoConvenio>{
    @Override
    public DocumentoConvenio toEntity(DocumentoConvenioDTO dto) {
        if(dto == null) return null;
        DocumentoConvenio entity = new DocumentoConvenio();
        entity.setId(dto.getId());
        entity.setNombreArchivo(dto.getNombreArchivo());
        entity.setUrl(dto.getUrl());
        entity.setTipoArchivo(dto.getTipoArchivo());

        if(dto.getSubidoPorId() != null){
            Usuario usuario= new Usuario();
            usuario.setId(dto.getSubidoPorId());
            entity.setSubidoPor(usuario);
        }

        entity.setFechaSubida(dto.getFechaSubida());

        return entity;
    }

    @Override
    public DocumentoConvenioDTO toDto(DocumentoConvenio entity) {
        DocumentoConvenioDTO dto = new DocumentoConvenioDTO();
        dto.setId(entity.getId());
        dto.setNombreArchivo(entity.getNombreArchivo());
        dto.setUrl(entity.getUrl());
        dto.setTipoArchivo(entity.getTipoArchivo());
        dto.setSubidoPorId(entity.getSubidoPor() != null ? entity.getSubidoPor().getId() : null);

        dto.setFechaSubida(entity.getFechaSubida());

        return dto;
    }

    @Override
    public List<DocumentoConvenio> toEntityList(List<DocumentoConvenioDTO> dtoList) {
        if (dtoList == null) return null;
        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<DocumentoConvenioDTO> toDtoList(List<DocumentoConvenio> entityList) {
        if (entityList == null) return null;
        return entityList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
