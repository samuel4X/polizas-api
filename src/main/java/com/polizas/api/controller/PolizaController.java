package com.polizas.api.controller;

import com.polizas.api.dto.CrearPolizaRequestDTO;
import com.polizas.api.dto.CrearRiesgoRequestDTO;
import com.polizas.api.dto.PolizaResponseDTO;
import com.polizas.api.dto.RiesgoResponseDTO;
import com.polizas.api.enums.EstadoPoliza;
import com.polizas.api.enums.TipoPoliza;
import com.polizas.api.service.PolizaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/polizas")
public class PolizaController {
    private PolizaService polizaService;

    @Autowired
    public void setPolizaService(PolizaService polizaService) {
        this.polizaService = polizaService;
    }

    @GetMapping
    public List<PolizaResponseDTO> listarPolizas(
            @RequestParam(required = false) TipoPoliza tipo,
            @RequestParam(required = false) EstadoPoliza estado
    ) {

        return polizaService.listarPolizas(tipo, estado);
    }

    @GetMapping("/{id}/riesgos")
    public List<RiesgoResponseDTO> obtenerRiesgos(@PathVariable Long id) {
        return polizaService.obtenerRiesgosPorPoliza(id);
    }

    @PostMapping("/{id}/renovar")
    public PolizaResponseDTO renovarPoliza(@PathVariable Long id) {
        return polizaService.renovarPoliza(id);
    }

    @PostMapping("/{id}/cancelar")
    public PolizaResponseDTO cancelarPoliza(@PathVariable Long id) {
        return polizaService.cancelarPoliza(id);
    }

    @PostMapping("/{id}/riesgos")
    public RiesgoResponseDTO agregarRiesgo(
            @PathVariable Long id,
            @RequestBody CrearRiesgoRequestDTO request) {

        return polizaService.agregarRiesgo(id, request);
    }

    @PostMapping
    public PolizaResponseDTO crearPoliza(@RequestBody CrearPolizaRequestDTO request) {
        return polizaService.crearPoliza(request);
    }
}
