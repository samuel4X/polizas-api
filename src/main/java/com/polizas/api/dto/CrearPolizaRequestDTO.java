package com.polizas.api.dto;

import com.polizas.api.enums.TipoPoliza;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CrearPolizaRequestDTO {

    private TipoPoliza tipo;

    private BigDecimal canonMensual;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;
}
