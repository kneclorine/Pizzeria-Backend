package com.example.demo.controller;

import javax.validation.Valid;

import com.example.demo.application.pizzaApplication.CreateOrUpdatePizzaDTO;
import com.example.demo.application.pizzaApplication.PizzaApplication;
import com.example.demo.application.pizzaApplication.PizzaDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pizzas")
public class PizzaController {
    private final PizzaApplication pizzaApplication;

    @Autowired
    public PizzaController(PizzaApplication pizzaApplication){
        this.pizzaApplication = pizzaApplication;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@Valid @RequestBody final CreateOrUpdatePizzaDTO dto){
        PizzaDTO PizzaDTO = this.pizzaApplication.add(dto);

        return ResponseEntity.status(201).body(PizzaDTO);
    }
}
