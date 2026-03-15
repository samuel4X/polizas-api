package com.polizas.api.service;

import com.polizas.api.dto.RiesgoResponseDTO;
import com.polizas.api.entity.Riesgo;
import com.polizas.api.enums.EstadoRiesgo;
import com.polizas.api.mapper.RiesgoMapper;
import com.polizas.api.repository.RiesgoRepository;
import org.springframework.stereotype.Service;

@Service
public class RiesgoService {

    private final RiesgoRepository repository;

    public RiesgoService(RiesgoRepository repository) {
        this.repository = repository;
    }

    public RiesgoResponseDTO cancelarRiesgo(long riesgoId) {
        Riesgo riesgo = repository.findById(riesgoId)
                .orElseThrow(() -> new IllegalStateException("No se encuentró el riesgo"));

        riesgo.setEstado(EstadoRiesgo.CANCELADO);
        repository.save(riesgo);

        return RiesgoMapper.toDTO(riesgo);
    }

}
