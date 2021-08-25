package com.example.demo.core.exceptionHandlers;

import com.example.demo.core.exceptions.BadRequestException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class BadRequestExceptionHandler {
   
    private final Logger logger;
    
    @Autowired
    public BadRequestExceptionHandler(final Logger logger){
        this.logger = logger;
    }

    @ExceptionHandler(value = {BadRequestException.class})
    protected ResponseEntity<Object> handleConflict(BadRequestException ex, WebRequest request) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ex.getMessage());
        stringBuilder.append(ex.getStackTrace().toString());
        
        logger.warn(stringBuilder.toString());

        return ResponseEntity.status(ex.getCode()).body(ex.getExceptions());
    }
}
