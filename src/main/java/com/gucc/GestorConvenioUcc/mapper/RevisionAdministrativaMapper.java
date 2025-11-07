package com.gucc.GestorConvenioUcc.mapper;

import com.gucc.GestorConvenioUcc.dto.RevisionAdministrativaDTO;
import com.gucc.GestorConvenioUcc.entity.RevisionAdministrativa;
import com.gucc.GestorConvenioUcc.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RevisionAdministrativaMapper
        extends RevisionBaseMapper<RevisionAdministrativa, RevisionAdministrativaDTO> {

    @Override
    public RevisionAdministrativaDTO toDto(RevisionAdministrativa entity) {
        if (entity == null) return null;
        RevisionAdministrativaDTO dto = new RevisionAdministrativaDTO();
        mapBaseEntityToDto(entity, dto);
        return dto;
    }

    @Override
    public RevisionAdministrativa toEntity(RevisionAdministrativaDTO dto) {
        if (dto == null) return null;
        RevisionAdministrativa entity = new RevisionAdministrativa();
        entity.setId(dto.getId());
        entity.setFechaRevision(dto.getFechaRevision());
        entity.setAprobada(dto.getAprobada());
        entity.setRazonRechazo(dto.getRazonRechazo());

        if (dto.getRevisorId() != null) {
            Usuario revisor = new Usuario();
            revisor.setId(dto.getRevisorId());
            entity.setRevisorAdministrativo(revisor);
        }
        return entity;
    }

    @Override protected Long getId(RevisionAdministrativa e) { return e.getId(); }
    @Override protected Date getFechaRevision(RevisionAdministrativa e) { return e.getFechaRevision(); }
    @Override protected Boolean getAprobada(RevisionAdministrativa e) { return e.getAprobada(); }
    @Override protected String getRazonRechazo(RevisionAdministrativa e) { return e.getRazonRechazo(); }
    @Override protected Object getRevisor(RevisionAdministrativa e) { return e.getRevisorAdministrativo(); }
    @Override protected Long getRevisorId(Object r) { return ((Usuario) r).getId(); }
}
