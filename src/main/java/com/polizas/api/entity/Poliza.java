package com.polizas.api.entity;

import com.polizas.api.enums.EstadoPoliza;
import com.polizas.api.enums.TipoPoliza;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Poliza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoPoliza tipo;

    @Enumerated(EnumType.STRING)
    private EstadoPoliza estado;

    private BigDecimal canonMensual;

    private BigDecimal prima;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    @OneToMany(mappedBy = "poliza")
    private List<Riesgo> riesgos;

}
