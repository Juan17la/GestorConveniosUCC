package com.gucc.GestorConvenioUcc.mapper;

import com.gucc.GestorConvenioUcc.dto.RevisionJuridicaDTO;
import com.gucc.GestorConvenioUcc.entity.RevisionJuridica;
import com.gucc.GestorConvenioUcc.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class RevisionJuridicaMapper
        extends RevisionBaseMapper<RevisionJuridica, RevisionJuridicaDTO> {

    @Override
    public RevisionJuridicaDTO toDto(RevisionJuridica entity) {
        if (entity == null) return null;
        RevisionJuridicaDTO dto = new RevisionJuridicaDTO();
        mapBaseEntityToDto(entity, dto);
        return dto;
    }

    @Override
    public RevisionJuridica toEntity(RevisionJuridicaDTO dto) {
        if (dto == null) return null;
        RevisionJuridica entity = new RevisionJuridica();
        entity.setId(dto.getId());
        entity.setFechaRevision(dto.getFechaRevision());
        entity.setAprobada(dto.getAprobada());
        entity.setRazonRechazo(dto.getRazonRechazo());

        if (dto.getRevisorId() != null) {
            Usuario revisor = new Usuario();
            revisor.setId(dto.getRevisorId());
            entity.setRevisorJuridico(revisor);
        }
        return entity;
    }

    @Override protected Long getId(RevisionJuridica e) { return e.getId(); }
    @Override protected Date getFechaRevision(RevisionJuridica e) { return e.getFechaRevision(); }
    @Override protected Boolean getAprobada(RevisionJuridica e) { return e.getAprobada(); }
    @Override protected String getRazonRechazo(RevisionJuridica e) { return e.getRazonRechazo(); }
    @Override protected Object getRevisor(RevisionJuridica e) { return e.getRevisorJuridico(); }
    @Override protected Long getRevisorId(Object r) { return ((Usuario) r).getId(); }
}
