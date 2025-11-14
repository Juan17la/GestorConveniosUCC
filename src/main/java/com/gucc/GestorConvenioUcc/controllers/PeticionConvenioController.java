package com.gucc.GestorConvenioUcc.controllers;

import com.gucc.GestorConvenioUcc.dto.PeticionConvenioDTO;
import com.gucc.GestorConvenioUcc.dto.RevisionRequest;
import com.gucc.GestorConvenioUcc.entity.PeticionConvenio;
import com.gucc.GestorConvenioUcc.service.PeticionConvenioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/peticion")
@RequiredArgsConstructor
public class PeticionConvenioController {

    private final PeticionConvenioService peticionConvenioService;

    @PostMapping(value= "/create", consumes = "multipart/form-data")
    public ResponseEntity<PeticionConvenioDTO> create(
            @RequestPart("request") PeticionConvenioDTO request,
            @RequestPart("archivos") List<MultipartFile> archivos
    ) throws IOException {
        return ResponseEntity.ok(peticionConvenioService.create(request, archivos));
    }

    @PostMapping(value = "/revision/administrativa/aprobada/{id}")
    public ResponseEntity<PeticionConvenioDTO> aprobarRevisionAdministrativa(
            @PathVariable Long id,
            @RequestBody RevisionRequest request
            ) throws IOException {
        return ResponseEntity.ok(peticionConvenioService.setRevisionAdministrativa(id, request.getIdRevisor(), request.getFechaRevision()));
    }

}
