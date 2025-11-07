package com.gucc.GestorConvenioUcc.controllers;

import com.gucc.GestorConvenioUcc.dto.RevisionJuridicaDTO;
import com.gucc.GestorConvenioUcc.service.RevisionJuridicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/revision/juridica")
@RequiredArgsConstructor
public class RevisionJuridicaController {

    private final RevisionJuridicaService revisionJuridicaService;

    @PostMapping(value = "/create")
    public ResponseEntity<RevisionJuridicaDTO> create(@RequestBody RevisionJuridicaDTO revisionJuridicaDTO){
        return ResponseEntity.ok(revisionJuridicaService.create(revisionJuridicaDTO));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        revisionJuridicaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
