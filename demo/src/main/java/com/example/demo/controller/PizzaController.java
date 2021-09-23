package com.example.demo.controller;

import java.util.UUID;

import javax.validation.Valid;

import com.example.demo.application.pizzaApplication.CreateOrUpdatePizzaDTO;
import com.example.demo.application.pizzaApplication.PizzaApplication;
import com.example.demo.application.pizzaApplication.PizzaDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/pizzas")
@ResponseStatus(HttpStatus.CREATED)
public class PizzaController {
    private final PizzaApplication pizzaApplication;

    @Autowired
    public PizzaController(PizzaApplication pizzaApplication){
        this.pizzaApplication = pizzaApplication;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PizzaDTO> create(@Valid @RequestBody CreateOrUpdatePizzaDTO dto){
        Mono<PizzaDTO> PizzaDTO = this.pizzaApplication.add(dto);

        return PizzaDTO;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,  path = "/{id}")
    public Mono<ResponseEntity<PizzaDTO>> get(@Valid @PathVariable UUID id) {
        Mono<PizzaDTO> pizzaDTO = this.pizzaApplication.get(id);
        return pizzaDTO.map( pizza -> ResponseEntity.ok(pizza)).defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
