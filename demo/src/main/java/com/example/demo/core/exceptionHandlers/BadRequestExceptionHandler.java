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

        
        logger.warn(String.format("%s , StackTrace: %s", ex.getMessage(), ex.getStackTrace()));

        return ResponseEntity.status(ex.getCode()).body(ex.getExceptions());
    }
}
