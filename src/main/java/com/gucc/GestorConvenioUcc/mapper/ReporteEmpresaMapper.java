package com.gucc.GestorConvenioUcc.mapper;

import com.gucc.GestorConvenioUcc.dto.ReporteEmpresaDTO;
import com.gucc.GestorConvenioUcc.entity.ReporteEmpresa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReporteEmpresaMapper {
    ReporteEmpresaMapper INSTANCE = Mappers.getMapper(ReporteEmpresaMapper.class);

    @Mapping(source = "empresa.id", target = "empresaId")
    @Mapping(source = "empresa.nombre", target = "empresaNombre")
    @Mapping(source = "convenio.id", target = "convenioId")
    @Mapping(source = "convenio.nombreConvenio", target = "convenioNombre")
    ReporteEmpresaDTO toDTO(ReporteEmpresa reporteEmpresa);

    @Mapping(source = "empresaId", target = "empresa.id")
    @Mapping(target = "empresa.nombre", ignore = true)
    @Mapping(target = "empresa.nit", ignore = true)
    @Mapping(target = "empresa.direccion", ignore = true)
    @Mapping(target = "empresa.telefono", ignore = true)
    @Mapping(target = "empresa.representante", ignore = true)
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
    ReporteEmpresa toEntity(ReporteEmpresaDTO reporteEmpresaDTO);
}
