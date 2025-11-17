package com.gucc.GestorConvenioUcc.controllers;

import com.gucc.GestorConvenioUcc.dto.EmpresaDTO;
import com.gucc.GestorConvenioUcc.dto.PeticionDTO;
import com.gucc.GestorConvenioUcc.service.PeticionService;
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

    private final PeticionService service;
    private final ObjectMapper objectMapper;

    @PostMapping(value= "/create", consumes = "multipart/form-data")
    public ResponseEntity<PeticionDTO> create(
            @RequestPart("empresaRequest") String empresaJson,
            @RequestPart("peticionRequest") String peticionJson,
            @RequestPart("minuta") MultipartFile minuta
    ) throws IOException {

        EmpresaDTO empresaRequest = objectMapper.readValue(empresaJson, EmpresaDTO.class);
        PeticionDTO peticionRequest = objectMapper.readValue(peticionJson, PeticionDTO.class);

        return ResponseEntity.ok(service.create(peticionRequest, empresaRequest, minuta));
    }
}
