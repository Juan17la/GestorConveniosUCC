package com.gucc.GestorConvenioUcc.mapper;

import com.gucc.GestorConvenioUcc.dto.FirmaConvenioDTO;
import com.gucc.GestorConvenioUcc.entity.FirmaConvenio;
import com.gucc.GestorConvenioUcc.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FirmaConvenioMapper implements BaseMapper<FirmaConvenioDTO, FirmaConvenio>{
    @Override
    public FirmaConvenioDTO toDto(FirmaConvenio entity) {
        if (entity == null) return null;

        FirmaConvenioDTO dto = new FirmaConvenioDTO();
        dto.setId(entity.getId());
        dto.setFechaFirma(entity.getFechaFirma());
        dto.setDirectivoFirmanteId(entity.getDirectivoFirmante() != null ? entity.getDirectivoFirmante().getId() : null);
        dto.setFirmado(entity.getFirmado());
        dto.setRazonRechazo(entity.getRazonRechazo());
        return dto;
    }

    @Override
    public FirmaConvenio toEntity(FirmaConvenioDTO dto) {
        if(dto == null) return null;
        FirmaConvenio entity = new FirmaConvenio();
        entity.setId(dto.getId());
        entity.setFechaFirma(dto.getFechaFirma());
        entity.setFirmado(dto.getFirmado());
        entity.setRazonRechazo(dto.getRazonRechazo());
        if (dto.getDirectivoFirmanteId() != null){
            Usuario usuario = new Usuario();
            usuario.setId(dto.getDirectivoFirmanteId());
            entity.setDirectivoFirmante(usuario);
        }
        return entity;
    }


    @Override
    public List<FirmaConvenio> toEntityList(List<FirmaConvenioDTO> dtoList) {
        if (dtoList == null) return null;
        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<FirmaConvenioDTO> toDtoList(List<FirmaConvenio> entityList) {
        if (entityList == null) return null;
        return entityList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
