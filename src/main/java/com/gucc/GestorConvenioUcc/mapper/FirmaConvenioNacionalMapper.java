package com.gucc.GestorConvenioUcc.mapper;

import com.gucc.GestorConvenioUcc.dto.FirmaConvenioNacionalDTO;
import com.gucc.GestorConvenioUcc.entity.FirmaConvenioNacional;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FirmaConvenioNacionalMapper {
    FirmaConvenioNacionalMapper INSTANCE = Mappers.getMapper(FirmaConvenioNacionalMapper.class);

    @Mapping(source = "revisor.id", target = "revisorId")
    @Mapping(source = "peticion.id", target = "peticionId")
    @Mapping(source = "documentoFirmado.id", target = "documentoFirmadoId")
    FirmaConvenioNacionalDTO toDTO(FirmaConvenioNacional firmaConvenioNacional);

    @Mapping(source = "revisorId", target = "revisor.id")
    @Mapping(target = "revisor.apellidos", ignore = true)
    @Mapping(target = "revisor.email", ignore = true)
    @Mapping(target = "revisor.contrasena", ignore = true)
    @Mapping(target = "revisor.campus", ignore = true)
    @Mapping(target = "revisor.rol", ignore = true)
    @Mapping(target = "revisor.fechaCreacion", ignore = true)
    @Mapping(target = "revisor.fechaUltimoLogin", ignore = true)
    @Mapping(source = "peticionId", target = "peticion.id")
    @Mapping(target = "peticion.fechaInicio", ignore = true)
    @Mapping(target = "peticion.fechaFinalizacion", ignore = true)
    @Mapping(target = "peticion.tipo", ignore = true)
    @Mapping(target = "peticion.creadoPor", ignore = true)
    @Mapping(target = "peticion.campus", ignore = true)
    @Mapping(target = "peticion.revisionJuridica", ignore = true)
    @Mapping(target = "peticion.firmaConvenioCampus", ignore = true)
    @Mapping(target = "peticion.firmaConvenioNacional", ignore = true)
    @Mapping(target = "peticion.documentos", ignore = true)
    @Mapping(target = "peticion.convenio", ignore = true)
    @Mapping(target = "peticion.fechaCreacion", ignore = true)
    @Mapping(source = "documentoFirmadoId", target = "documentoFirmado.id")
    @Mapping(target = "documentoFirmado.url", ignore = true)
    @Mapping(target = "documentoFirmado.tipoArchivo", ignore = true)
    @Mapping(target = "documentoFirmado.subidoPor", ignore = true)
    @Mapping(target = "documentoFirmado.fechaSubida", ignore = true)
    @Mapping(target = "documentoFirmado.peticion", ignore = true)
    @Mapping(target = "documentoFirmado.convenio", ignore = true)
    FirmaConvenioNacional toEntity(FirmaConvenioNacionalDTO firmaConvenioNacionalDTO);
}
