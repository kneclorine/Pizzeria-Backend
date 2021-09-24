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
        ingredient.setThisNew(true);
        ingredient.validate("name", ingredient.getName(), (name) -> this.ingredientWriteRepository.exists(name));

        return this.ingredientWriteRepository.add(ingredient).flatMap( monoIngredient -> {
            logger.info(this.serializeObject(monoIngredient, "added"));
            return Mono.just(this.modelMapper.map(monoIngredient, IngredientDTO.class));
        });
    }

    @Override
    public Mono<IngredientDTO> get(UUID id) {

        return this.findById(id).flatMap( dbingredient -> Mono.just(this.modelMapper.map(dbingredient, IngredientDTO.class)));
    }

    @Override
    public Mono<IngredientDTO> update(UUID id, CreateOrUpdateIngredientDTO dto) {

        return this.findById(id).flatMap( dbIngredient -> {
            if(dbIngredient.getName().equals(dto.getName())){
                this.modelMapper.map(dto, dbIngredient);
                dbIngredient.validate();
                return this.ingredientWriteRepository.update(dbIngredient).flatMap(ingredient -> Mono.just(this.modelMapper.map(ingredient, IngredientDTO.class)));
            } else{
                this.modelMapper.map(dto, dbIngredient);
                dbIngredient.validate("name", dbIngredient.getName(), (name) -> this.ingredientWriteRepository.exists(name));
                return this.ingredientWriteRepository.update(dbIngredient).flatMap(ingredient -> {
                    logger.info(this.serializeObject(dbIngredient, "updated"));
                    return Mono.just(this.modelMapper.map(ingredient, IngredientDTO.class));
                });
            }   
        });
    }

    @Override
    public Mono<IngredientDTO> delete(UUID id) {
        
        return this.findById(id).flatMap(ingredient -> {
            logger.info(this.serializeObject(ingredient, "deleted")); 
            return this.ingredientWriteRepository.delete(ingredient).then(Mono.just(this.modelMapper.map(ingredient, IngredientDTO.class)));}
        );
    }

    @Override
    public Flux<IngredientProjection> getAll(String name, int page, int size) {
        return this.ingredientReadRepository.getAll(name, page, size);
    }
}
