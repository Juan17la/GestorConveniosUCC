package com.gucc.GestorConvenioUcc.controllers;

import com.gucc.GestorConvenioUcc.dto.RenovacionConvenioDTO;
import com.gucc.GestorConvenioUcc.dto.RequestRenovacionConvenio;
import com.gucc.GestorConvenioUcc.entity.RenovacionConvenio;
import com.gucc.GestorConvenioUcc.service.RenovacionConvenioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/convenio")
@RequiredArgsConstructor
public class ConvenioController {

    private final RenovacionConvenioService serviceRenovacion;

    @PostMapping("/renovar/{id}")
    public ResponseEntity<RenovacionConvenioDTO> renovarConvenio(
            @PathVariable("id") Long convenio_id,
            @RequestBody RequestRenovacionConvenio request
            ){
        return ResponseEntity.ok(serviceRenovacion.create(convenio_id, request));
    }

}
