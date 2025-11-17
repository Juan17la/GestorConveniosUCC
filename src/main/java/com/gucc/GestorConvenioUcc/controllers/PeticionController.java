package com.gucc.GestorConvenioUcc.controllers;

import com.gucc.GestorConvenioUcc.dto.EmpresaDTO;
import com.gucc.GestorConvenioUcc.dto.PeticionDTO;
import com.gucc.GestorConvenioUcc.dto.RevisionJuridicaDTO;
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

}
