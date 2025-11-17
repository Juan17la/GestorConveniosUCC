package com.gucc.GestorConvenioUcc.mapper;

import com.gucc.GestorConvenioUcc.dto.PeticionDTO;
import com.gucc.GestorConvenioUcc.entity.Documento;
import com.gucc.GestorConvenioUcc.entity.Peticion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PeticionMapper {
    PeticionMapper INSTANCE = Mappers.getMapper(PeticionMapper.class);

    @Mapping(source = "creadoPor.id", target = "creadoPorId")
    @Mapping(source = "campus.id", target = "campusId")
    @Mapping(source = "empresa.id", target = "empresaId")
    @Mapping(source = "revisionJuridica.id", target = "revisionJuridicaId")
    @Mapping(source = "firmaConvenioCampus.id", target = "firmaConvenioCampusId")
    @Mapping(source = "firmaConvenioNacional.id", target = "firmaConvenioNacionalId")
    @Mapping(source = "documentos", target = "documentoIds", qualifiedByName = "documentosToIds")
    @Mapping(source = "convenio.id", target = "convenioId")
    PeticionDTO toDTO(Peticion peticion);

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
    @Mapping(source = "empresaId", target = "empresa.id")
    @Mapping(target = "empresa.nombre", ignore = true)
    @Mapping(target = "empresa.nit", ignore = true)
    @Mapping(target = "empresa.direccion", ignore = true)
    @Mapping(target = "empresa.telefono", ignore = true)
    @Mapping(target = "empresa.representante", ignore = true)
    @Mapping(target = "revisionJuridica", ignore = true)
    @Mapping(target = "firmaConvenioCampus", ignore = true)
    @Mapping(target = "firmaConvenioNacional", ignore = true)
    @Mapping(target = "documentos", ignore = true)
    @Mapping(target = "convenio", ignore = true)
    Peticion toEntity(PeticionDTO peticionDTO);

    @Named("documentosToIds")
    default List<Long> documentosToIds(List<Documento> documentos) {
        if (documentos == null) {
            return null;
        }
        return documentos.stream()
                .map(Documento::getId)
                .collect(Collectors.toList());
    }
}
