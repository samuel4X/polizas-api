package com.polizas.api.mapper;

import com.polizas.api.dto.RiesgoResponseDTO;
import com.polizas.api.entity.Riesgo;

import java.util.List;

public class RiesgoMapper {

    public static RiesgoResponseDTO toDTO(Riesgo riesgo){
        RiesgoResponseDTO dto = new RiesgoResponseDTO();

        dto.setId(riesgo.getId());
        dto.setInmueble(riesgo.getInmueble());
        dto.setCanon(riesgo.getCanon());
        dto.setArrendador(riesgo.getArrendador());
        dto.setArrendatario(riesgo.getArrendatario());

        return dto;
    }

    public static List<RiesgoResponseDTO> toDTOList(List<Riesgo> riesgos){
        return riesgos.stream()
                .map(RiesgoMapper::toDTO)
                .toList();
    }
}
