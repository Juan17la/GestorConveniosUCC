package com.gucc.GestorConvenioUcc.controllers;

import com.gucc.GestorConvenioUcc.dto.ReporteEmpresaDTO;
import com.gucc.GestorConvenioUcc.service.ReporteEmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes-empresa")
@RequiredArgsConstructor
public class ReporteEmpresaController {

    private final ReporteEmpresaService reporteService;

    /**
     * Crea un nuevo reporte de empresa
     * POST /api/reportes-empresa
     */
    @PostMapping
    public ResponseEntity<ReporteEmpresaDTO> crearReporte(@RequestBody ReporteEmpresaDTO dto) {
        ReporteEmpresaDTO creado = reporteService.crearReporte(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    /**
     * Obtiene todos los reportes de un convenio
     * GET /api/reportes-empresa/convenio/{convenioId}
     */
    @GetMapping("/convenio/{convenioId}")
    public ResponseEntity<List<ReporteEmpresaDTO>> obtenerReportesPorConvenio(
            @PathVariable Long convenioId) {
        List<ReporteEmpresaDTO> reportes = reporteService.obtenerReportesPorConvenio(convenioId);
        return ResponseEntity.ok(reportes);
    }

    /**
     * Obtiene todos los reportes de una empresa
     * GET /api/reportes-empresa/empresa/{empresaId}
     */
    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<ReporteEmpresaDTO>> obtenerReportesPorEmpresa(
            @PathVariable Long empresaId) {
        List<ReporteEmpresaDTO> reportes = reporteService.obtenerReportesPorEmpresa(empresaId);
        return ResponseEntity.ok(reportes);
    }

    /**
     * Obtiene reportes de una empresa en un convenio específico
     * GET /api/reportes-empresa/empresa/{empresaId}/convenio/{convenioId}
     */
    @GetMapping("/empresa/{empresaId}/convenio/{convenioId}")
    public ResponseEntity<List<ReporteEmpresaDTO>> obtenerReportesPorEmpresaYConvenio(
            @PathVariable Long empresaId,
            @PathVariable Long convenioId) {
        List<ReporteEmpresaDTO> reportes = reporteService.obtenerReportesPorEmpresaYConvenio(
                empresaId, convenioId);
        return ResponseEntity.ok(reportes);
    }

    /**
     * Actualiza un reporte existente
     * PUT /api/reportes-empresa/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<ReporteEmpresaDTO> actualizarReporte(
            @PathVariable Long id,
            @RequestBody ReporteEmpresaDTO dto) {
        ReporteEmpresaDTO actualizado = reporteService.actualizarReporte(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    /**
     * Elimina un reporte
     * DELETE /api/reportes-empresa/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReporte(@PathVariable Long id) {
        reporteService.eliminarReporte(id);
        return ResponseEntity.noContent().build();
    }
}
