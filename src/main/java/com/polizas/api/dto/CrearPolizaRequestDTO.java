package com.polizas.api.dto;

import com.polizas.api.enums.TipoPoliza;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CrearPolizaRequestDTO {
    @NotNull(message = "Es requerido")
    private TipoPoliza tipo;
    @NotNull(message = "Es requerido")
    @Positive(message = "Debe ser un valor positivo")
    private BigDecimal canonMensual;
    @NotNull
    private LocalDate fechaInicio;
    @NotNull
    private LocalDate fechaFin;
}
