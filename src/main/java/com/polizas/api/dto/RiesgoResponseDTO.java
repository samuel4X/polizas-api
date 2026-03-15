package com.polizas.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RiesgoResponseDTO {

    private Long id;

    private String inmueble;

    private BigDecimal canon;

    private String arrendador;

    private String arrendatario;

}
