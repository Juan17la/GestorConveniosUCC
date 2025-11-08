package com.gucc.GestorConvenioUcc.mapper;

import com.gucc.GestorConvenioUcc.dto.RevisionBaseDTO;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public abstract class RevisionBaseMapper<E, D extends RevisionBaseDTO> implements BaseMapper<D, E> {

    protected void mapBaseEntityToDto(E entity, D dto) {
        if (entity == null || dto == null) return;

        dto.setId(getId(entity));
        dto.setFechaRevision(getFechaRevision(entity));
        dto.setAprobada(getAprobada(entity));
        dto.setRazonRechazo(getRazonRechazo(entity));

        Object revisor = getRevisor(entity);
        if (revisor != null) {
            dto.setRevisorId(getRevisorId(revisor));
        }
    }

    @Override
    public List<E> toEntityList(List<D> dtoList) {
        if (dtoList == null) return null;
        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<D> toDtoList(List<E> entityList) {
        if (entityList == null) return null;
        return entityList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // Métodos abstractos específicos para cada mapper
    protected abstract Long getId(E entity);
    protected abstract Date getFechaRevision(E entity);
    protected abstract Boolean getAprobada(E entity);
    protected abstract String getRazonRechazo(E entity);
    protected abstract Object getRevisor(E entity);
    protected abstract Long getRevisorId(Object revisor);
}
