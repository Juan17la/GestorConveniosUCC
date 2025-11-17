package com.gucc.GestorConvenioUcc.mapper;

import com.gucc.GestorConvenioUcc.dto.DocumentoDTO;
import com.gucc.GestorConvenioUcc.entity.Documento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DocumentoMapper {
    DocumentoMapper INSTANCE = Mappers.getMapper(DocumentoMapper.class);

    @Mapping(source = "subidoPor.id", target = "subidoPorId")
    @Mapping(source = "peticion.id", target = "peticionId")
    @Mapping(source = "convenio.id", target = "convenioId")
    DocumentoDTO toDTO(Documento documento);

    @Mapping(source = "subidoPorId", target = "subidoPor.id")
    @Mapping(target = "subidoPor.apellidos", ignore = true)
    @Mapping(target = "subidoPor.email", ignore = true)
    @Mapping(target = "subidoPor.contrasena", ignore = true)
    @Mapping(target = "subidoPor.campus", ignore = true)
    @Mapping(target = "subidoPor.rol", ignore = true)
    @Mapping(target = "subidoPor.fechaCreacion", ignore = true)
    @Mapping(target = "subidoPor.fechaUltimoLogin", ignore = true)
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
    @Mapping(source = "convenioId", target = "convenio.id")
    @Mapping(target = "convenio.fechaInicio", ignore = true)
    @Mapping(target = "convenio.fechaFinalizacion", ignore = true)
    @Mapping(target = "convenio.tipo", ignore = true)
    @Mapping(target = "convenio.creadoPor", ignore = true)
    @Mapping(target = "convenio.campus", ignore = true)
    @Mapping(target = "convenio.supervisadoPor", ignore = true)
    @Mapping(target = "convenio.empresa", ignore = true)
    @Mapping(target = "convenio.peticionCreacion", ignore = true)
    @Mapping(target = "convenio.documentos", ignore = true)
    @Mapping(target = "convenio.reportesEmpresa", ignore = true)
    @Mapping(target = "convenio.alertasVencimiento", ignore = true)
    @Mapping(target = "convenio.renovaciones", ignore = true)
    @Mapping(target = "convenio.fechaCreacion", ignore = true)
    @Mapping(target = "convenio.estado", ignore = true)
    Documento toEntity(DocumentoDTO documentoDTO);
}
