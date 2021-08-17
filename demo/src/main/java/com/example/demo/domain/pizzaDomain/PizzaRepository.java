package com.example.demo.domain.pizzaDomain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PizzaRepository {
    public void add(Pizza pizza);
    public void update(Pizza pizza);
    public void delete(Pizza pizza);
    public Optional<Pizza> get(UUID id);
    public List<Pizza> getAll(String name, BigDecimal price, int page, int size);
}
