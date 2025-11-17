package com.gucc.GestorConvenioUcc.mapper;

import com.gucc.GestorConvenioUcc.dto.UsuarioDTO;
import com.gucc.GestorConvenioUcc.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    @Mapping(source = "campus.id", target = "campusId")
    @Mapping(source = "campus.nombre", target = "campusNombre")
    UsuarioDTO toDTO(Usuario usuario);

    @Mapping(source = "campusId", target = "campus.id")
    @Mapping(target = "campus.nombre", ignore = true)
    @Mapping(target = "campus.direccion", ignore = true)
    @Mapping(target = "campus.fechaCreacion", ignore = true)

    @Mapping(source = "contrasena", target = "contrasena")
    Usuario toEntity(UsuarioDTO usuarioDTO);
}
