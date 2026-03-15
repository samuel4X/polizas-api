package com.polizas.api.controller;

import com.polizas.api.dto.RiesgoResponseDTO;
import com.polizas.api.service.RiesgoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/riesgos")
public class RiesgoController {

    private final RiesgoService riesgoService;

    public RiesgoController(RiesgoService riesgoService) {
        this.riesgoService = riesgoService;
    }

    @PostMapping("/{id}/cancelar")
    public RiesgoResponseDTO cancelarRiesgo(@PathVariable Long id) {
        return riesgoService.cancelarRiesgo(id);
    }

}
