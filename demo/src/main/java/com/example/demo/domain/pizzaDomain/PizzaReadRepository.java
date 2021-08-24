package com.example.demo.domain.pizzaDomain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PizzaReadRepository {
    public Optional<Pizza> findById(UUID id);
    
    public List<PizzaProyection> getAll(String name, BigDecimal price ,int page, int size);
}
