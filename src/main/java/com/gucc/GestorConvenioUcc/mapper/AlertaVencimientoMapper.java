package com.gucc.GestorConvenioUcc.mapper;

import com.gucc.GestorConvenioUcc.dto.AlertaVencimientoDTO;
import com.gucc.GestorConvenioUcc.entity.AlertaVencimiento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AlertaVencimientoMapper {
    AlertaVencimientoMapper INSTANCE = Mappers.getMapper(AlertaVencimientoMapper.class);

    @Mapping(source = "convenio.id", target = "convenioId")
    @Mapping(source = "convenio.nombreConvenio", target = "convenioNombre")
    @Mapping(source = "enviadaA.id", target = "enviadaAId")
    @Mapping(source = "enviadaA.nombre", target = "enviadaANombre")
    AlertaVencimientoDTO toDTO(AlertaVencimiento alertaVencimiento);

    @Mapping(source = "convenioId", target = "convenio.id")
    @Mapping(target = "convenio.nombreConvenio", ignore = true)
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
    @Mapping(source = "enviadaAId", target = "enviadaA.id")
    @Mapping(target = "enviadaA.nombre", ignore = true)
    @Mapping(target = "enviadaA.apellidos", ignore = true)
    @Mapping(target = "enviadaA.email", ignore = true)
    @Mapping(target = "enviadaA.contrasena", ignore = true)
    @Mapping(target = "enviadaA.campus", ignore = true)
    @Mapping(target = "enviadaA.rol", ignore = true)
    @Mapping(target = "enviadaA.fechaCreacion", ignore = true)
    @Mapping(target = "enviadaA.fechaUltimoLogin", ignore = true)
    AlertaVencimiento toEntity(AlertaVencimientoDTO alertaVencimientoDTO);
}
