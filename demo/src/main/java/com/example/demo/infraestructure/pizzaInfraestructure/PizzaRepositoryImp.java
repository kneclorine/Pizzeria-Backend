package com.example.demo.infraestructure.pizzaInfraestructure;

import java.util.List;
import java.util.UUID;

import com.example.demo.domain.pizzaDomain.Pizza;
import com.example.demo.domain.repositoryDomain.Repository;

public class PizzaRepositoryImp implements Repository<Pizza> {

    @Override
    public void add(Pizza type) {
    }

    @Override
    public void update(Pizza type) {
    }

    @Override
    public void delete(Pizza type) {
    }

    @Override
    public Pizza get(UUID id) {
        return null;
    }

    @Override
    public List<Pizza> getAll() {
        return null;
    }
    
}
