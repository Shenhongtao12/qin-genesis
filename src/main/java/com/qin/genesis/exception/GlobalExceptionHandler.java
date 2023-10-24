package com.qin.genesis.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public final class GlobalExceptionHandler {

    @Autowired(required = false)
    private CustomExceptionHandler customExceptionHandler;

    @Autowired
    private ApplicationContext applicationContext;

    @ExceptionHandler(value = Throwable.class)
    public ResponseEntity<ExceptionResult> handler(Throwable t) {
        return customExceptionHandler.handle(t);
    }

}
