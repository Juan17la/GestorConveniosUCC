package com.gucc.GestorConvenioUcc.mapper;

import com.gucc.GestorConvenioUcc.dto.ConvenioDTO;
import com.gucc.GestorConvenioUcc.entity.AlertaVencimiento;
import com.gucc.GestorConvenioUcc.entity.Convenio;
import com.gucc.GestorConvenioUcc.entity.Documento;
import com.gucc.GestorConvenioUcc.entity.ReporteEmpresa;
import com.gucc.GestorConvenioUcc.entity.RenovacionConvenio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ConvenioMapper {
    ConvenioMapper INSTANCE = Mappers.getMapper(ConvenioMapper.class);

    @Mapping(source = "creadoPor.id", target = "creadoPorId")
    @Mapping(source = "campus.id", target = "campusId")
    @Mapping(source = "supervisadoPor.id", target = "supervisadoPorId")
    @Mapping(source = "empresa.id", target = "empresaId")
    @Mapping(source = "peticionCreacion.id", target = "peticionCreacionId")
    @Mapping(source = "documentos", target = "documentoIds", qualifiedByName = "documentosToIds")
    @Mapping(source = "reportesEmpresa", target = "reporteEmpresaIds", qualifiedByName = "reportesEmpresaToIds")
    @Mapping(source = "alertasVencimiento", target = "alertasVencimientoIds", qualifiedByName = "alertasVencimientoToIds")
    @Mapping(source = "renovaciones", target = "renovacionesIds", qualifiedByName = "renovacionesToIds")
    ConvenioDTO toDTO(Convenio convenio);

    @Mapping(source = "creadoPorId", target = "creadoPor.id")
    @Mapping(target = "creadoPor.nombre", ignore = true)
    @Mapping(target = "creadoPor.apellidos", ignore = true)
    @Mapping(target = "creadoPor.email", ignore = true)
    @Mapping(target = "creadoPor.contrasena", ignore = true)
    @Mapping(target = "creadoPor.campus", ignore = true)
    @Mapping(target = "creadoPor.rol", ignore = true)
    @Mapping(target = "creadoPor.fechaCreacion", ignore = true)
    @Mapping(target = "creadoPor.fechaUltimoLogin", ignore = true)
    @Mapping(source = "campusId", target = "campus.id")
    @Mapping(target = "campus.nombre", ignore = true)
    @Mapping(target = "campus.direccion", ignore = true)
    @Mapping(target = "campus.fechaCreacion", ignore = true)
    @Mapping(source = "supervisadoPorId", target = "supervisadoPor.id")
    @Mapping(target = "supervisadoPor.nombre", ignore = true)
    @Mapping(target = "supervisadoPor.apellidos", ignore = true)
    @Mapping(target = "supervisadoPor.email", ignore = true)
    @Mapping(target = "supervisadoPor.contrasena", ignore = true)
    @Mapping(target = "supervisadoPor.campus", ignore = true)
    @Mapping(target = "supervisadoPor.rol", ignore = true)
    @Mapping(target = "supervisadoPor.fechaCreacion", ignore = true)
    @Mapping(target = "supervisadoPor.fechaUltimoLogin", ignore = true)
    @Mapping(source = "empresaId", target = "empresa.id")
    @Mapping(target = "empresa.nombre", ignore = true)
    @Mapping(target = "empresa.nit", ignore = true)
    @Mapping(target = "empresa.direccion", ignore = true)
    @Mapping(target = "empresa.telefono", ignore = true)
    @Mapping(target = "empresa.representante", ignore = true)
    @Mapping(source = "peticionCreacionId", target = "peticionCreacion.id")
    @Mapping(target = "peticionCreacion.nombrePeticion", ignore = true)
    @Mapping(target = "peticionCreacion.fechaInicio", ignore = true)
    @Mapping(target = "peticionCreacion.fechaFinalizacion", ignore = true)
    @Mapping(target = "peticionCreacion.tipo", ignore = true)
    @Mapping(target = "peticionCreacion.creadoPor", ignore = true)
    @Mapping(target = "peticionCreacion.campus", ignore = true)
    @Mapping(target = "peticionCreacion.empresa", ignore = true)
    @Mapping(target = "peticionCreacion.revisionJuridica", ignore = true)
    @Mapping(target = "peticionCreacion.firmaConvenioCampus", ignore = true)
    @Mapping(target = "peticionCreacion.firmaConvenioNacional", ignore = true)
    @Mapping(target = "peticionCreacion.documentos", ignore = true)
    @Mapping(target = "peticionCreacion.convenio", ignore = true)
    @Mapping(target = "peticionCreacion.fechaCreacion", ignore = true)
    @Mapping(target = "documentos", ignore = true)
    @Mapping(target = "reportesEmpresa", ignore = true)
    @Mapping(target = "alertasVencimiento", ignore = true)
    @Mapping(target = "renovaciones", ignore = true)
    Convenio toEntity(ConvenioDTO convenioDTO);

    @Named("documentosToIds")
    default List<Long> documentosToIds(List<Documento> documentos) {
        if (documentos == null) {
            return null;
        }
        return documentos.stream()
                .map(Documento::getId)
                .collect(Collectors.toList());
    }

    @Named("reportesEmpresaToIds")
    default List<Long> reportesEmpresaToIds(List<ReporteEmpresa> reportes) {
        if (reportes == null) {
            return null;
        }
        return reportes.stream()
                .map(ReporteEmpresa::getId)
                .collect(Collectors.toList());
    }

    @Named("alertasVencimientoToIds")
    default List<Long> alertasVencimientoToIds(List<AlertaVencimiento> alertas) {
        if (alertas == null) {
            return null;
        }
        return alertas.stream()
                .map(AlertaVencimiento::getId)
                .collect(Collectors.toList());
    }

    @Named("renovacionesToIds")
    default List<Long> renovacionesToIds(List<RenovacionConvenio> renovaciones) {
        if (renovaciones == null) {
            return null;
        }
        return renovaciones.stream()
                .map(RenovacionConvenio::getId)
                .collect(Collectors.toList());
    }
}
