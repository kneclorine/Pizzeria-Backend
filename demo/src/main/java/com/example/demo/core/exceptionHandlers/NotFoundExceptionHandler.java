package com.example.demo.core.exceptionHandlers;

import com.example.demo.core.exceptions.NotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class NotFoundExceptionHandler {
   
    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<Object> handleConflict(NotFoundException ex, WebRequest request) {

        return ResponseEntity.status(ex.getCode()).body("Error 404");
    }
}
