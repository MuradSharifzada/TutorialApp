package com.tutorial.Tutorial.Exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Getter

public class ErrorResponse {
    private final String message;
    private final HttpStatus status;
    private final int statusCode;
    private LocalDateTime localDateTime;

    public ErrorResponse(HttpStatus status,int statusCode, String message, LocalDateTime localDateTime) {
        this.status = status;
        this.statusCode = status.value();
        this.message = message;
        this.localDateTime = localDateTime;
    }

}
