package com.polizas.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CrearRiesgoRequestDTO {
    @NotNull
    private String inmueble;
    @NotNull
    @Positive
    private BigDecimal canon;
    @NotNull
    private String arrendador;
    @NotNull
    private String arrendatario;
}
