package com.gucc.GestorConvenioUcc.mapper;

import com.gucc.GestorConvenioUcc.dto.RevisionAdministrativaDTO;
import com.gucc.GestorConvenioUcc.entity.RevisionAdministrativa;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RevisionAdministrativaMapper implements BaseMapper<RevisionAdministrativaDTO, RevisionAdministrativa>{

    @Override
    public RevisionAdministrativa toEntity(RevisionAdministrativaDTO dto){
        if (dto == null) return null;

        RevisionAdministrativa revisionAdministrativa = new RevisionAdministrativa();
        revisionAdministrativa.setId(dto.getId());
        revisionAdministrativa.setFechaRevision(dto.getFechaRevision());
        revisionAdministrativa.setAprobada(dto.getAprobada());
        revisionAdministrativa.setRazonRechazo(dto.getRazonRechazo());
        return revisionAdministrativa;
    }

    @Override
    public RevisionAdministrativaDTO toDto(RevisionAdministrativa entity) {
        if (entity == null) return null;

        RevisionAdministrativaDTO dto = new RevisionAdministrativaDTO();
        dto.setId(entity.getId());
        dto.setFechaRevision(entity.getFechaRevision());
        dto.setAprobada(entity.getAprobada());
        dto.setRazonRechazo(entity.getRazonRechazo());

        return dto;
    }

    @Override
    public List<RevisionAdministrativa> toEntityList(List<RevisionAdministrativaDTO> dtoList) {
        if (dtoList == null) return null;
        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<RevisionAdministrativaDTO> toDtoList(List<RevisionAdministrativa> entityList) {
        if (entityList == null) return null;
        return entityList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

}
