package com.polizas.api.entity;

import com.polizas.api.enums.EstadoRiesgo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Riesgo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String inmueble;

    private BigDecimal canon;

    private String arrendador;

    private String arrendatario;

    @Enumerated(EnumType.STRING)
    private EstadoRiesgo estado;

    @ManyToOne
    @JoinColumn(name = "poliza_id")
    private Poliza poliza;
}
