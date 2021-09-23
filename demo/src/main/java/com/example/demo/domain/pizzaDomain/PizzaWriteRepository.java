package com.example.demo.domain.pizzaDomain;

import java.util.UUID;

import com.example.demo.core.functionalInterfaces.ExistsByField;
import com.example.demo.core.functionalInterfaces.FindById;

import reactor.core.publisher.Mono;

public interface PizzaWriteRepository extends FindById<Pizza, UUID>, ExistsByField{

    public Mono<Pizza> add(Pizza pizza);
}
