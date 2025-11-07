package com.gucc.GestorConvenioUcc.controllers;

import com.gucc.GestorConvenioUcc.dto.CampusDTO;
import com.gucc.GestorConvenioUcc.dto.RevisionAdministrativaDTO;
import com.gucc.GestorConvenioUcc.service.RevisionAdministrativaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/revision/administrativa")
@RequiredArgsConstructor
public class RevisionAdministrativaController {

    private final RevisionAdministrativaService revisionAdministrativaService;

    @PostMapping(value = "/create")
    public ResponseEntity<RevisionAdministrativaDTO> create(@RequestBody RevisionAdministrativaDTO revisionAdministrativaDTO){
        return ResponseEntity.ok(revisionAdministrativaService.create(revisionAdministrativaDTO));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        revisionAdministrativaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
