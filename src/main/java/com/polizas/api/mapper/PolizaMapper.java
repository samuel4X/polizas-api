package com.polizas.api.mapper;

import com.polizas.api.dto.CrearRiesgoRequestDTO;
import com.polizas.api.dto.PolizaResponseDTO;
import com.polizas.api.entity.Poliza;

import java.util.List;

public class PolizaMapper {

    public static PolizaResponseDTO toDTO(Poliza poliza){
        PolizaResponseDTO dto = new PolizaResponseDTO();

        dto.setId(poliza.getId());
        dto.setTipo(poliza.getTipo());
        dto.setEstado(poliza.getEstado());
        dto.setCanonMensual(poliza.getCanonMensual());
        dto.setPrima(poliza.getPrima());
        dto.setFechaInicio(poliza.getFechaInicio());
        dto.setFechaFin(poliza.getFechaFin());

        return dto;
    }

    public static List<PolizaResponseDTO> toDTOList(List<Poliza> polizas){
        return polizas.stream()
                .map(PolizaMapper::toDTO)
                .toList();
    }

}
