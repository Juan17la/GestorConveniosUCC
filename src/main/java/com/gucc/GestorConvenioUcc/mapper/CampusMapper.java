package com.gucc.GestorConvenioUcc.mapper;

import com.gucc.GestorConvenioUcc.dto.CampusDTO;
import com.gucc.GestorConvenioUcc.entity.Campus;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CampusMapper {
    CampusMapper INSTANCE = Mappers.getMapper(CampusMapper.class);

    CampusDTO toDTO(Campus campus);

    Campus toEntity(CampusDTO campusDTO);
}
