package com.ChallengeFinal.Alura.ForoHub.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ErrorResponse {
    private int statusCode;
    private String message;
    private LocalDateTime timestamp;
    private String details;

    public ErrorResponse(int statusCode, String message, LocalDateTime timestamp, String details) {
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = timestamp;
        this.details = details;
    }

}
