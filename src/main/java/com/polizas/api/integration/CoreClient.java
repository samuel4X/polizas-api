package com.polizas.api.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CoreClient {
    private static final Logger logger = LoggerFactory.getLogger(CoreClient.class);

    public void enviarEventoActualizacion(Long polizaId) {
        logger.info("Enviando evento al CORE -> evento: ACTUALIZACION, polizaId: {}", polizaId);
    }
}