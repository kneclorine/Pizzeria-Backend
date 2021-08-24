package com.example.demo.infraestructure.pizzaInfraestructure;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.domain.pizzaDomain.Pizza;
import com.example.demo.domain.pizzaDomain.PizzaProyection;
import com.example.demo.domain.pizzaDomain.PizzaReadRepository;
import com.example.demo.domain.pizzaDomain.PizzaWriteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
public class PizzaRepositoryImp implements PizzaWriteRepository, PizzaReadRepository {

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
    public Optional<Pizza> findById(UUID id) {
        return this.pizzaJPARepository.findById(id);
    }

    @Override
    public List<PizzaProyection> getAll(String name, BigDecimal price, int page, int size) {
        return this.pizzaJPARepository.findByCriteria(name,
        price, PageRequest.of(page, size));
    }

}
