package com.polizas.api.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> argumentosInvalidos(
            IllegalArgumentException ex) {

        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                400,
                LocalDateTime.now()
        );

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> estadoInvalido(Exception ex) {

        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                400,
                LocalDateTime.now()
        );

        return ResponseEntity.badRequest().body(error);
    }

}