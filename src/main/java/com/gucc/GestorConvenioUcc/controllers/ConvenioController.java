package com.gucc.GestorConvenioUcc.controllers;

import com.gucc.GestorConvenioUcc.dto.ConvenioDTO;
import com.gucc.GestorConvenioUcc.dto.RenovacionConvenioDTO;
import com.gucc.GestorConvenioUcc.dto.RequestRenovacionConvenio;
import com.gucc.GestorConvenioUcc.entity.Convenio;
import com.gucc.GestorConvenioUcc.entity.RenovacionConvenio;
import com.gucc.GestorConvenioUcc.service.ConvenioService;
import com.gucc.GestorConvenioUcc.service.RenovacionConvenioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/convenio")
@RequiredArgsConstructor
public class ConvenioController {

    private final RenovacionConvenioService serviceRenovacion;
    private final ConvenioService convenioService;

    @PostMapping("/renovar/{id}")
    public ResponseEntity<RenovacionConvenioDTO> renovarConvenio(
            @PathVariable("id") Long convenio_id,
            @RequestBody RequestRenovacionConvenio request
            ){
        return ResponseEntity.ok(serviceRenovacion.create(convenio_id, request));
    }

    // GETS PARA FRONT
    @GetMapping("/convenios")
    public ResponseEntity<List<ConvenioDTO>> getAllConvenios() {
        return ResponseEntity.ok(convenioService.getAllConvenios());
    }


}
