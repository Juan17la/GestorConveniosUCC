package com.gucc.GestorConvenioUcc.mapper;

import com.gucc.GestorConvenioUcc.dto.PeticionConvenioDTO;
import com.gucc.GestorConvenioUcc.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PeticionConvenioMapper implements BaseMapper<PeticionConvenioDTO, PeticionConvenio> {

    @Autowired
    private RevisionAdministrativaMapper revisionAdministrativaMapper;
    @Autowired
    private RevisionJuridicaMapper revisionJuridicaMapper;
    @Autowired
    private FirmaConvenioMapper firmaConvenioMapper;
    @Autowired
    private DocumentoConvenioMapper documentoConvenioMapper;

    @Override
    public PeticionConvenio toEntity(PeticionConvenioDTO dto) {
        if (dto == null) return null;
        PeticionConvenio entity = new PeticionConvenio();
        entity.setId(dto.getId());
        entity.setFechaCreacion(dto.getFechaCreacion());
        entity.setTipo(dto.getTipo());
        entity.setEstado(dto.getEstado());

        if (dto.getCreadorId() != null) {
            Usuario usuario = new Usuario();
            usuario.setId(dto.getCreadorId());
            entity.setCreador(usuario);
        }

        if (dto.getCampusId() != null) {
            Campus campus = new Campus();
            campus.setId(dto.getCampusId());
            entity.setCampus(campus);
        }

        if (dto.getRevisionAdministrativa() != null)
            entity.setRevisionAdministrativa(revisionAdministrativaMapper.toEntity(dto.getRevisionAdministrativa()));

        if (dto.getRevisionJuridica() != null)
            entity.setRevisionJuridica(revisionJuridicaMapper.toEntity(dto.getRevisionJuridica()));

        if (dto.getFirmaConvenio() != null)
            entity.setFirmaConvenio(firmaConvenioMapper.toEntity(dto.getFirmaConvenio()));

        if (dto.getDocumentos() != null)
            entity.setDocumentos(documentoConvenioMapper.toEntityList(dto.getDocumentos()));

        return entity;
    }

    @Override
    public PeticionConvenioDTO toDto(PeticionConvenio entity) {
        if (entity == null) return null;
        PeticionConvenioDTO dto = new PeticionConvenioDTO();
        dto.setId(entity.getId());
        dto.setFechaCreacion(entity.getFechaCreacion());
        dto.setTipo(entity.getTipo());
        dto.setEstado(entity.getEstado());
        dto.setCreadorId(entity.getCreador() != null ? entity.getCreador().getId() : null);
        dto.setCampusId(entity.getCampus() != null ? entity.getCampus().getId() : null);
        dto.setRevisionAdministrativa(revisionAdministrativaMapper.toDto(entity.getRevisionAdministrativa()));
        dto.setRevisionJuridica(revisionJuridicaMapper.toDto(entity.getRevisionJuridica()));
        dto.setFirmaConvenio(firmaConvenioMapper.toDto(entity.getFirmaConvenio()));
        dto.setDocumentos(documentoConvenioMapper.toDtoList(entity.getDocumentos()));
        return dto;
    }

    @Override
    public List<PeticionConvenio> toEntityList(List<PeticionConvenioDTO> dtoList) {
        if (dtoList == null) return null;
        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<PeticionConvenioDTO> toDtoList(List<PeticionConvenio> entityList) {
        if (entityList == null) return null;
        return entityList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
