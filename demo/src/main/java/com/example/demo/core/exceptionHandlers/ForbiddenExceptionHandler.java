package com.example.demo.core.exceptionHandlers;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.Forbidden;
import org.springframework.web.context.request.WebRequest;

public class ForbiddenExceptionHandler<ForbiddenException> {
    
    private final Logger logger;
    
    @Autowired
    public ForbiddenExceptionHandler(final Logger logger){
        this.logger = logger;
    }
   
    @ExceptionHandler(value = {Forbidden.class})
    protected ResponseEntity<Object> handleConflict(Forbidden ex, WebRequest request) {
        logger.warn(String.format("%s , StackTrace: %s", ex.getMessage(), ex.getStackTrace().toString()));
        return ResponseEntity.status(ex.getStatusCode()).body("Error 403, Session expired");
    }
}
