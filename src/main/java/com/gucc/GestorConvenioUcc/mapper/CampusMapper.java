package com.gucc.GestorConvenioUcc.mapper;

import com.gucc.GestorConvenioUcc.dto.CampusDTO;
import com.gucc.GestorConvenioUcc.entity.Campus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CampusMapper implements BaseMapper<CampusDTO, Campus> {

    @Override
    public Campus toEntity(CampusDTO dto) {
        if (dto == null) return null;

        Campus campus = new Campus();
        campus.setId(dto.getId());
        campus.setNombre(dto.getNombre());
        campus.setDireccion(dto.getDireccion());

        return campus;
    }

    @Override
    public CampusDTO toDto(Campus entity) {
        if (entity == null) return null;

        CampusDTO dto = new CampusDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setDireccion(entity.getDireccion());

        return dto;
    }

    @Override
    public List<Campus> toEntityList(List<CampusDTO> dtoList) {
        if (dtoList == null) return null;
        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<CampusDTO> toDtoList(List<Campus> entityList) {
        if (entityList == null) return null;
        return entityList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
