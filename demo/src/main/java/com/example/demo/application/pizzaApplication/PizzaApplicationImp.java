package com.example.demo.application.pizzaApplication;

import java.util.UUID;

import com.example.demo.application.imageApplication.ImageApplicationImp;
import com.example.demo.application.ingredientApplication.IngredientApplicationImp;
import com.example.demo.core.ApplicationBase;
import com.example.demo.domain.imageDomain.Image;
import com.example.demo.domain.ingredientDomain.Ingredient;
import com.example.demo.domain.pizzaDomain.Pizza;
import com.example.demo.domain.pizzaDomain.PizzaWriteRepository;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class PizzaApplicationImp extends ApplicationBase<Pizza, UUID> implements PizzaApplication {

    private final PizzaWriteRepository pizzaWriteRepository;
    private final IngredientApplicationImp ingredientApplicationImp;
    private final ImageApplicationImp imageApplicationImp;
    private final ModelMapper modelMapper = new ModelMapper();
    private final Logger logger;

    @Autowired
    public PizzaApplicationImp(final PizzaWriteRepository pizzaWriteRepository,
            final IngredientApplicationImp ingredientApplicationImp, final ImageApplicationImp imageApplicationImp, final Logger logger) {

        super((id) -> pizzaWriteRepository.findById(id));

        this.pizzaWriteRepository = pizzaWriteRepository;
        this.ingredientApplicationImp = ingredientApplicationImp;
        this.imageApplicationImp = imageApplicationImp;
        this.logger = logger;
    }

    @Override
    public Mono<PizzaDTO> add(CreateOrUpdatePizzaDTO dto) {

        Pizza pizza = this.modelMapper.map(dto, Pizza.class);
        pizza.setId(UUID.randomUUID());
        for (UUID ingredientId : dto.getIngredients()) {

            ingredientApplicationImp.get(ingredientId).flatMap(dbIngredient ->{
                Ingredient ingredient = this.modelMapper.map(dbIngredient, Ingredient.class);
                pizza.addIngredient(ingredient);
                return Mono.just(ingredient);
            });            
        }
        pizza.setPrice(pizza.calculatePrice());

        Image image = modelMapper.map(this.imageApplicationImp.get(dto.getImage()), Image.class);
        pizza.setImage(image.getId());

        pizza.setThisNew(true);
        pizza.validate("name", pizza.getName(), (name) -> this.pizzaWriteRepository.exists(name));

        return this.pizzaWriteRepository.add(pizza).flatMap(monoPizza -> {
            logger.info(this.serializeObject(pizza, "added"));
            return Mono.just(this.modelMapper.map(pizza, PizzaDTO.class));
        });
    }

    @Override
    public Mono<PizzaDTO> get(UUID id) {
        
        return this.findById(id).flatMap( pizza -> Mono.just(this.modelMapper.map(pizza, PizzaDTO.class)));
    }
}