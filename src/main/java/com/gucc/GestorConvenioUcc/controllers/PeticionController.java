package com.gucc.GestorConvenioUcc.controllers;

import com.gucc.GestorConvenioUcc.dto.*;
import com.gucc.GestorConvenioUcc.entity.FirmaConvenioNacional;
import com.gucc.GestorConvenioUcc.service.FirmaConvenioCampusService;
import com.gucc.GestorConvenioUcc.service.FirmaConvenioNacionalService;
import com.gucc.GestorConvenioUcc.service.PeticionService;
import com.gucc.GestorConvenioUcc.service.RevisionJuridicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;

@RestController
@RequestMapping("/api/peticion")
@RequiredArgsConstructor
public class PeticionController {

    private final PeticionService servicePeticion;
    private final RevisionJuridicaService serviceRevisionJuridica;
    private final FirmaConvenioCampusService serviceFirmaCampus;
    private final FirmaConvenioNacionalService serviceFirmaNacional;
    private final ObjectMapper objectMapper;

    @PostMapping(value= "/create", consumes = "multipart/form-data")
    public ResponseEntity<PeticionDTO> create(
            @RequestPart("empresaRequest") String empresaJson,
            @RequestPart("peticionRequest") String peticionJson,
            @RequestPart("minuta") MultipartFile minuta
    ) throws IOException {

        EmpresaDTO empresaRequest = objectMapper.readValue(empresaJson, EmpresaDTO.class);
        PeticionDTO peticionRequest = objectMapper.readValue(peticionJson, PeticionDTO.class);

        return ResponseEntity.ok(servicePeticion.create(peticionRequest, empresaRequest, minuta));
    }

    @PostMapping(value = "/revision/juridica/aprobada/{id}")
    public ResponseEntity<RevisionJuridicaDTO> aprobarRevisionJuridica(
            @RequestBody RevisionJuridicaDTO request,
            @PathVariable("id") Long peticionId
    ) {
        request.setPeticionId(peticionId);
        return ResponseEntity.ok(serviceRevisionJuridica.create(request));
    }

    @PostMapping(value= "/firma/campus/{id}", consumes = "multipart/form-data")
    public ResponseEntity<FirmaConvenioCampusDTO> firmaCampus(
                @PathVariable("id") long peticionId,
            @RequestPart("firmaRequest") String firmaJson,
            @RequestPart("firmaCampusDocumento") MultipartFile firmaCampusDocumento
    ) throws IOException {

        FirmaConvenioCampusDTO firmaCampusRequest = objectMapper.readValue(firmaJson, FirmaConvenioCampusDTO.class);
        firmaCampusRequest.setPeticionId(peticionId);

        return ResponseEntity.ok(serviceFirmaCampus.create(firmaCampusRequest, firmaCampusDocumento));
    }

    @PostMapping(value= "/firma/nacional/{id}", consumes = "multipart/form-data")
    public ResponseEntity<FirmaConvenioNacionalDTO> firmaNacional(
            @PathVariable("id") long peticionId,
            @RequestPart("firmaRequest") String firmaJson,
            @RequestPart("firmaNacionalDocumento") MultipartFile firmaNacionalDocumento
    ) throws IOException {

        FirmaConvenioNacionalDTO firmaNacionalRequest = objectMapper.readValue(firmaJson, FirmaConvenioNacionalDTO.class);
        firmaNacionalRequest.setPeticionId(peticionId);

        return ResponseEntity.ok(serviceFirmaNacional.create(firmaNacionalRequest, firmaNacionalDocumento));
    }
}
