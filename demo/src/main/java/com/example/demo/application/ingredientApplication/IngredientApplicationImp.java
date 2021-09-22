package com.example.demo.application.ingredientApplication;

import java.util.UUID;

import com.example.demo.core.ApplicationBase;
import com.example.demo.domain.ingredientDomain.Ingredient;
import com.example.demo.domain.ingredientDomain.IngredientProjection;
import com.example.demo.domain.ingredientDomain.IngredientReadRepository;
import com.example.demo.domain.ingredientDomain.IngredientWriteRepository;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class IngredientApplicationImp extends ApplicationBase<Ingredient, UUID> implements IngredientApplication {

    private final IngredientWriteRepository ingredientWriteRepository;
    private final IngredientReadRepository ingredientReadRepository;
    private final ModelMapper modelMapper;
    private final Logger logger;

    @Autowired
    public IngredientApplicationImp(final IngredientWriteRepository ingredientWriteRepository,
            final IngredientReadRepository ingredientReadRepository, final ModelMapper modelMapper,
            final Logger logger) {

        super((id) -> ingredientWriteRepository.findById(id));

        this.ingredientWriteRepository = ingredientWriteRepository;
        this.ingredientReadRepository = ingredientReadRepository;
        this.modelMapper = modelMapper;
        this.logger = logger;
    }

    @Override
    public Mono<IngredientDTO> add(CreateOrUpdateIngredientDTO dto) {

        Ingredient ingredient = modelMapper.map(dto, Ingredient.class);
        ingredient.setId(UUID.randomUUID());
        ingredient.validate("name", ingredient.getName(), (name) -> this.ingredientWriteRepository.exists(name));

        this.ingredientWriteRepository.add(ingredient);
        logger.info(this.serializeObject(ingredient, "added"));

        return Mono.just(modelMapper.map(ingredient, IngredientDTO.class));
    }

    @Override
    public Mono<IngredientDTO> get(UUID id) {

        //TODO: Esto no se si esta bien
        Ingredient ingredient = this.findById(id).block();
        return Mono.just(this.modelMapper.map(ingredient, IngredientDTO.class));
    }

    @Override
    public Mono<IngredientDTO> update(UUID id, CreateOrUpdateIngredientDTO dto) {

        //TODO: Esto no se si esta bien
        Ingredient ingredient = this.findById(id).block();
        
        if (ingredient.getName().equals(dto.getName())){
            this.modelMapper.map(dto, ingredient);
            ingredient.validate();
        } else {
            this.modelMapper.map(dto, ingredient);
            ingredient.validate("name", ingredient.getName(), (name) -> this.ingredientWriteRepository.exists(name));
        }
        this.ingredientWriteRepository.update(ingredient);
        logger.info(this.serializeObject(ingredient, "updated"));

        return Mono.just(this.modelMapper.map(ingredient, IngredientDTO.class));
    }

    @Override
    public Mono<IngredientDTO> delete(UUID id) {
        logger.info("Ingredient with id: "+id+" deleted."); 
        return this.findById(id)
        .flatMap(ingredient -> this.ingredientWriteRepository.delete(ingredient)
        .then(Mono.just(this.modelMapper.map(ingredient, IngredientDTO.class))));
    }

    @Override
    public Flux<IngredientProjection> getAll(String name, int page, int size) {
        return this.ingredientReadRepository.getAll(name, page, size);
    }
}
