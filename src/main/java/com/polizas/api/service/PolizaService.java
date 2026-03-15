package com.polizas.api.service;

import com.polizas.api.dto.*;
import com.polizas.api.entity.Poliza;
import com.polizas.api.entity.Riesgo;
import com.polizas.api.enums.EstadoPoliza;
import com.polizas.api.enums.EstadoRiesgo;
import com.polizas.api.enums.TipoPoliza;
import com.polizas.api.integration.CoreClient;
import com.polizas.api.mapper.PolizaMapper;
import com.polizas.api.mapper.RiesgoMapper;
import com.polizas.api.repository.PolizaRepository;
import com.polizas.api.repository.RiesgoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PolizaService {

    private final PolizaRepository polizaRepository;
    private final RiesgoRepository riesgoRepository;
    private final CoreClient coreClient;

    public PolizaService(PolizaRepository polizaRepository, RiesgoRepository riesgoRepository, CoreClient coreClient) {
        this.polizaRepository = polizaRepository;
        this.riesgoRepository = riesgoRepository;
        this.coreClient = coreClient;
    }

    public List<PolizaResponseDTO> listarPolizas(TipoPoliza tipo , EstadoPoliza estado){
        if (estado == EstadoPoliza.CANCELADA){
            throw new IllegalStateException("No se encontro poliza activa");
        }

        List<Poliza> polizas;

        if (tipo != null && estado != null) {
            polizas = polizaRepository.findByTipoAndEstado(tipo, estado);
        }
        else if (tipo != null) {
            polizas = polizaRepository.findByTipo(tipo);
        }
        else if (estado != null) {
            polizas = polizaRepository.findByEstado(estado);
        }
        else {
            polizas = polizaRepository.findAll();
        }

        return PolizaMapper.toDTOList(polizas);
    }

    public List<RiesgoResponseDTO> obtenerRiesgosPorPoliza(Long polizaId) {

        polizaRepository.findById(polizaId)
                .orElseThrow(() -> new IllegalStateException("Poliza no encontrada"));

        List<Riesgo> riesgos = riesgoRepository.findByPolizaId(polizaId).stream()
                .filter(riesgo -> !(riesgo.getEstado() == EstadoRiesgo.CANCELADO)).toList();


        return RiesgoMapper.toDTOList(riesgos);
    }

    public RiesgoResponseDTO  agregarRiesgo(Long polizaId, CrearRiesgoRequestDTO request){
        Poliza poliza = polizaRepository.findById(polizaId).
                orElseThrow(() -> new IllegalStateException(("Poliza no encontrado")));

        if(poliza.getTipo() != TipoPoliza.COLECTIVA){
            List<Riesgo> riesgos = riesgoRepository.findByPolizaId(polizaId);

            if(!riesgos.isEmpty()){
                throw new  RuntimeException(("Una poliza individual solo puede tener un riesgo"));
            }
        }
        if(poliza.getEstado() == EstadoPoliza.CANCELADA) {
            throw new IllegalStateException("La poliza se encuentra cancelada");
        }

        Riesgo riesgo = new Riesgo();
        riesgo.setInmueble(request.getInmueble());
        riesgo.setCanon(request.getCanon());
        riesgo.setArrendador(request.getArrendador());
        riesgo.setArrendatario(request.getArrendatario());
        riesgo.setPoliza(poliza);

        riesgoRepository.save(riesgo);
        coreClient.enviarEventoActualizacion(riesgo.getId());
        return RiesgoMapper.toDTO(riesgo);
    }

    public PolizaResponseDTO renovarPoliza (Long polizaId, RenovarPolizaRequestDTO ipc){

        Poliza poliza = polizaRepository.findById(polizaId)
                .orElseThrow(() -> new IllegalStateException("Poliza no encontrada"));

        if(poliza.getEstado() == EstadoPoliza.CANCELADA){
            throw new IllegalStateException("No se puede renovar una poliza cancelada");
        }

        BigDecimal nuevoCanon = poliza.getCanonMensual().multiply(BigDecimal.ONE.add(ipc.getIpc()));
        long meses = ChronoUnit.MONTHS.between(poliza.getFechaInicio(), poliza.getFechaFin());
        BigDecimal prima = nuevoCanon.multiply(BigDecimal.valueOf(meses));

        poliza.setEstado(EstadoPoliza.RENOVADA);
        poliza.setCanonMensual(nuevoCanon);
        poliza.setPrima(prima);
        polizaRepository.save(poliza);

        coreClient.enviarEventoActualizacion(poliza.getId());
        return PolizaMapper.toDTO(poliza);
    }

    public PolizaResponseDTO cancelarPoliza(Long polizaId){
        Poliza poliza = polizaRepository.findById(polizaId).
                orElseThrow(() -> new IllegalStateException("Poliza no encontrada"));
        List<Riesgo> riesgos = riesgoRepository.findByPolizaId(polizaId);
        riesgos.forEach(riesgo -> riesgo.setEstado(EstadoRiesgo.CANCELADO));

        poliza.setEstado(EstadoPoliza.CANCELADA);
        riesgoRepository.saveAll(riesgos);
        polizaRepository.save(poliza);

        coreClient.enviarEventoActualizacion(poliza.getId());
        return PolizaMapper.toDTO(poliza);
    }

    public PolizaResponseDTO crearPoliza(CrearPolizaRequestDTO request){
        Poliza poliza = new Poliza();


        poliza.setTipo(request.getTipo());
        poliza.setCanonMensual(request.getCanonMensual());
        poliza.setFechaInicio(request.getFechaInicio());
        poliza.setFechaFin(request.getFechaFin());
        poliza.setEstado(EstadoPoliza.ACTIVA);
        if (poliza.getFechaInicio().isAfter(request.getFechaFin())) {
            throw new IllegalArgumentException("La fecha inicio no puede ser mayor a la final");
        }
        long meses = ChronoUnit.MONTHS.between(
                request.getFechaInicio(),
                request.getFechaFin()
        );

        BigDecimal prima =
                request.getCanonMensual().multiply(BigDecimal.valueOf(meses));

        poliza.setPrima(prima);
        polizaRepository.save(poliza);
        coreClient.enviarEventoActualizacion(poliza.getId());
        return PolizaMapper.toDTO(poliza);
    }
}
