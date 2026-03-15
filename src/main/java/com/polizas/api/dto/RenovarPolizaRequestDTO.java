package com.polizas.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RenovarPolizaRequestDTO {
    @NotNull
    private BigDecimal ipc;
}
