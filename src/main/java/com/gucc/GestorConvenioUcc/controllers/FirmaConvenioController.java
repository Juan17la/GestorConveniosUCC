package com.gucc.GestorConvenioUcc.controllers;

import com.gucc.GestorConvenioUcc.dto.FirmaConvenioDTO;
import com.gucc.GestorConvenioUcc.service.FirmaConvenioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/firma")
@RequiredArgsConstructor
public class FirmaConvenioController {

    private final FirmaConvenioService firmaConvenioService;

    @PostMapping(value = "/create")
    public ResponseEntity<FirmaConvenioDTO> create(@RequestBody FirmaConvenioDTO request){
       return ResponseEntity.ok(firmaConvenioService.create(request));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        firmaConvenioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
