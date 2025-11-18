package com.gucc.GestorConvenioUcc.service;

import com.gucc.GestorConvenioUcc.dto.RenovacionConvenioDTO;
import com.gucc.GestorConvenioUcc.dto.RequestRenovacionConvenio;
import com.gucc.GestorConvenioUcc.entity.Convenio;
import com.gucc.GestorConvenioUcc.entity.Peticion;
import com.gucc.GestorConvenioUcc.entity.RenovacionConvenio;
import com.gucc.GestorConvenioUcc.entity.Usuario;
import com.gucc.GestorConvenioUcc.enums.EstadoConvenio;
import com.gucc.GestorConvenioUcc.enums.EstadoPeticion;
import com.gucc.GestorConvenioUcc.mapper.RenovacionConvenioMapper;
import com.gucc.GestorConvenioUcc.repository.ConvenioRepository;
import com.gucc.GestorConvenioUcc.repository.PeticionRepository;
import com.gucc.GestorConvenioUcc.repository.RenovacionConvenioRepository;
import com.gucc.GestorConvenioUcc.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RenovacionConvenioService {

    private final RenovacionConvenioMapper mapperRenovacion;
    private final ConvenioRepository repositoryConvenio;
    private final PeticionRepository repositoryPeticion;
    private final UsuarioRepository repositoryUsuario;
    private final RenovacionConvenioRepository repositoryRenovacion;

    public RenovacionConvenioDTO create(Long convenio_id, RequestRenovacionConvenio request){

        Convenio convenio = repositoryConvenio.findById(convenio_id)
                .orElseThrow(() -> new RuntimeException("El convenio no existe"));

        if(repositoryRenovacion.existsByConvenioOriginalId(convenio_id)){
            throw new RuntimeException("Ya existe una renovación registrada para este convenio.");
        }

        List<EstadoPeticion> estadosBloqueantes = List.of(
                EstadoPeticion.EN_REVISION_JURIDICA,
                EstadoPeticion.EN_FIRMA_CAMPUS,
                EstadoPeticion.EN_FIRMA_NACIONAL
        );

        if(repositoryPeticion.existsByConvenioIdAndEstadoIn(convenio_id, estadosBloqueantes)){
            throw new RuntimeException("Ya existe una petición pendiente para este convenio.");
        }

        if(convenio.getEstado() == EstadoConvenio.ACTIVO){
            throw new RuntimeException("El convenio esta activo, no se puede realizar una renovacion");
        }

        Usuario creador = repositoryUsuario.findById(request.getCreadoPor())
                .orElseThrow(() -> new RuntimeException("Revisor no encontrado"));

        Peticion nuevaPeticion = Peticion.builder()
                .nombrePeticion(convenio.getNombreConvenio())
                .fechaInicio(request.getFechaInicio())
                .fechaFinalizacion(request.getFechaFinalizacion())
                .tipo(convenio.getTipo())
                .creadoPor(creador)
                .campus(convenio.getCampus())
                .empresa(convenio.getEmpresa())
                .convenio(convenio)
                .build();

        Peticion peticionSaved = repositoryPeticion.save(nuevaPeticion);

        RenovacionConvenio renovacion = RenovacionConvenio.builder()
                .convenioOriginal(convenio)
                .nuevaPeticion(peticionSaved)
                .build();

        RenovacionConvenio savedRenovacion = repositoryRenovacion.save(renovacion);

        return mapperRenovacion.toDTO(savedRenovacion);
    }


}
