package com.polizas.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/core-mock")
public class CoreMockController {

    private static final Logger logger =
            LoggerFactory.getLogger(CoreMockController.class);

    @PostMapping("/evento")
    public void recibirEvento(@RequestBody Map<String, Object> payload){

        logger.info("Evento recibido en CORE MOCK: {}", payload);

    }

}
