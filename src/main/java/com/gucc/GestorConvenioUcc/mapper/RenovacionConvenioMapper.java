package com.gucc.GestorConvenioUcc.mapper;

import com.gucc.GestorConvenioUcc.dto.RenovacionConvenioDTO;
import com.gucc.GestorConvenioUcc.entity.RenovacionConvenio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RenovacionConvenioMapper {
    RenovacionConvenioMapper INSTANCE = Mappers.getMapper(RenovacionConvenioMapper.class);

    @Mapping(source = "convenioOriginal.id", target = "convenioOriginalId")
    @Mapping(source = "nuevaPeticion.id", target = "nuevaPeticionId")
    RenovacionConvenioDTO toDTO(RenovacionConvenio renovacionConvenio);

    @Mapping(source = "convenioOriginalId", target = "convenioOriginal.id")
    @Mapping(target = "convenioOriginal.nombreConvenio", ignore = true)
    @Mapping(target = "convenioOriginal.fechaInicio", ignore = true)
    @Mapping(target = "convenioOriginal.fechaFinalizacion", ignore = true)
    @Mapping(target = "convenioOriginal.tipo", ignore = true)
    @Mapping(target = "convenioOriginal.creadoPor", ignore = true)
    @Mapping(target = "convenioOriginal.campus", ignore = true)
    @Mapping(target = "convenioOriginal.supervisadoPor", ignore = true)
    @Mapping(target = "convenioOriginal.empresa", ignore = true)
    @Mapping(target = "convenioOriginal.peticionCreacion", ignore = true)
    @Mapping(target = "convenioOriginal.documentos", ignore = true)
    @Mapping(target = "convenioOriginal.reportesEmpresa", ignore = true)
    @Mapping(target = "convenioOriginal.alertasVencimiento", ignore = true)
    @Mapping(target = "convenioOriginal.renovaciones", ignore = true)
    @Mapping(target = "convenioOriginal.fechaCreacion", ignore = true)
    @Mapping(target = "convenioOriginal.estado", ignore = true)
    @Mapping(source = "nuevaPeticionId", target = "nuevaPeticion.id")
    @Mapping(target = "nuevaPeticion.nombrePeticion", ignore = true)
    @Mapping(target = "nuevaPeticion.fechaInicio", ignore = true)
    @Mapping(target = "nuevaPeticion.fechaFinalizacion", ignore = true)
    @Mapping(target = "nuevaPeticion.tipo", ignore = true)
    @Mapping(target = "nuevaPeticion.creadoPor", ignore = true)
    @Mapping(target = "nuevaPeticion.campus", ignore = true)
    @Mapping(target = "nuevaPeticion.revisionJuridica", ignore = true)
    @Mapping(target = "nuevaPeticion.firmaConvenioCampus", ignore = true)
    @Mapping(target = "nuevaPeticion.firmaConvenioNacional", ignore = true)
    @Mapping(target = "nuevaPeticion.documentos", ignore = true)
    @Mapping(target = "nuevaPeticion.convenio", ignore = true)
    @Mapping(target = "nuevaPeticion.fechaCreacion", ignore = true)
    RenovacionConvenio toEntity(RenovacionConvenioDTO renovacionConvenioDTO);
}
