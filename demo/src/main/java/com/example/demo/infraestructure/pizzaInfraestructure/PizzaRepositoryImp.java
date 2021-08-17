package com.example.demo.infraestructure.pizzaInfraestructure;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.domain.pizzaDomain.Pizza;
import com.example.demo.domain.pizzaDomain.PizzaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

public class PizzaRepositoryImp implements PizzaRepository {

    private PizzaJPARepository pizzaJPARepository;

    @Autowired
    public PizzaRepositoryImp(PizzaJPARepository pizzaJPARepository){
        this.pizzaJPARepository = pizzaJPARepository;
    }

    @Override
    public void add(Pizza pizza) {
        this.pizzaJPARepository.save(pizza);
    }

    @Override
    public void update(Pizza pizza) {
        this.pizzaJPARepository.save(pizza);        
    }

    @Override
    public void delete(Pizza pizza) {
        this.pizzaJPARepository.delete(pizza);
    }

    @Override
    public Optional<Pizza> get(UUID id) {
        return this.pizzaJPARepository.findById(id);
    }

    @Override
    public List<Pizza> getAll(String name, BigDecimal price, int page, int size) {
        return this.pizzaJPARepository.findByCriteria(
            name, price,
            PageRequest.of(page, size)
            );
    }
}
