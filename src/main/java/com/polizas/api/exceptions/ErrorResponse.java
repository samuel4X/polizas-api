package com.polizas.api.exceptions;

import java.time.LocalDateTime;

public record ErrorResponse(String mensaje, int status, LocalDateTime fecha) {

}