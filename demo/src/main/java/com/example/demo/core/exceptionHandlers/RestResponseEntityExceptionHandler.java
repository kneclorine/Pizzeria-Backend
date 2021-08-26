package com.example.demo.core.exceptionHandlers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    // TODO: ConstraintViolationException, IllegalArgumentException

    private final Logger logger;

    @Autowired
    public RestResponseEntityExceptionHandler(final Logger logger) {
        this.logger = logger;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
                
       Map<String, String> errors = new HashMap<String, String>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            errors.put(error.getCodes()[error.getCodes().length - 3].substring(error.getCode().length() + 1),
                    error.getDefaultMessage());
        }

        logger.warn(String.format("%s , StackTrace: %s", ex.getMessage(), ex.getStackTrace().toString()));
        
        return ResponseEntity.status(400).body(errors);
    }
}