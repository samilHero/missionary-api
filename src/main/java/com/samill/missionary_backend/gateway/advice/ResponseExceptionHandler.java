package com.samill.missionary_backend.gateway.advice;

import lombok.NonNull;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Exception handleException(@NonNull Exception ex) {
        return ex;
    }
}
