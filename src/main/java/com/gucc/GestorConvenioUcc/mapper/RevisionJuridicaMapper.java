package com.gucc.GestorConvenioUcc.mapper;

import com.gucc.GestorConvenioUcc.dto.RevisionJuridicaDTO;
import com.gucc.GestorConvenioUcc.entity.RevisionJuridica;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RevisionJuridicaMapper {
    RevisionJuridicaMapper INSTANCE = Mappers.getMapper(RevisionJuridicaMapper.class);

    @Mapping(source = "revisor.id", target = "revisorId")
    @Mapping(source = "peticion.id", target = "peticionId")
    RevisionJuridicaDTO toDTO(RevisionJuridica revisionJuridica);

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
    RevisionJuridica toEntity(RevisionJuridicaDTO revisionJuridicaDTO);
}
