package com.polizas.api.repository;

import com.polizas.api.entity.Poliza;
import com.polizas.api.enums.EstadoPoliza;
import com.polizas.api.enums.TipoPoliza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PolizaRepository extends JpaRepository<Poliza, Long> {

    List<Poliza> findByTipoAndEstado(TipoPoliza tipo, EstadoPoliza estado);
    List<Poliza> findByTipo(TipoPoliza tipo);
    List<Poliza> findByEstado(EstadoPoliza estado);
}
