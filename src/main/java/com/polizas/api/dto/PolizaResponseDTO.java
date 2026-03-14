package com.polizas.api.dto;

import com.polizas.api.enums.EstadoPoliza;
import com.polizas.api.enums.TipoPoliza;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class PolizaResponseDTO {

    private Long id;

    private TipoPoliza tipo;

    private EstadoPoliza estado;

    private BigDecimal canonMensual;

    private BigDecimal prima;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;
}
