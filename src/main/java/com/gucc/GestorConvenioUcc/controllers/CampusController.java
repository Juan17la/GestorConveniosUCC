package com.gucc.GestorConvenioUcc.controllers;

import com.gucc.GestorConvenioUcc.dto.CampusDTO;
import com.gucc.GestorConvenioUcc.service.CampusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/campus/")
@RequiredArgsConstructor
public class CampusController {

    private final CampusService campusService;

    @PostMapping(value = "create")
    public ResponseEntity<CampusDTO> create(@RequestBody CampusDTO campusDTO) {
        return ResponseEntity.ok(campusService.create(campusDTO));
    }

    @PostMapping(value = "delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        campusService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
