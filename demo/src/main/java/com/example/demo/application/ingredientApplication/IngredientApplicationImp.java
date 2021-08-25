package com.example.demo.application.ingredientApplication;

import java.util.List;
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

@Service
public class IngredientApplicationImp extends ApplicationBase<Ingredient, UUID> implements IngredientApplication {

    private final IngredientWriteRepository ingredientWriteRepository;
    private final IngredientReadRepository ingredientReadRepository;
    private final ModelMapper modelMapper;
    private final Logger logger;

    @Autowired
    public IngredientApplicationImp(final IngredientWriteRepository ingredientWriteRepository,
                                    final IngredientReadRepository ingredientReadRepository,
                                    final ModelMapper modelMapper,
                                    final Logger logger){

        super((id) -> ingredientWriteRepository.findById(id));
        
        this.ingredientWriteRepository = ingredientWriteRepository;
        this.ingredientReadRepository = ingredientReadRepository;
        this.modelMapper = modelMapper;
        this.logger = logger;
    }

    @Override
    public IngredientDTO add(CreateOrUpdateIngredientDTO dto) {

        Ingredient ingredient = modelMapper.map(dto, Ingredient.class);
        ingredient.setId(UUID.randomUUID());
        ingredient.validate("name", ingredient.getName(), (name)-> this.ingredientWriteRepository.exists(name));

        this.ingredientWriteRepository.add(ingredient);
        logger.info(this.serializeObject(ingredient, "added"));

        return modelMapper.map(ingredient, IngredientDTO.class);
    }

    @Override
    public IngredientDTO get(UUID id) {

        Ingredient ingredient = this.findById(id);
        return this.modelMapper.map(ingredient, IngredientDTO.class);
    }

    @Override
    public void update(UUID id, CreateOrUpdateIngredientDTO dtos) {

        Ingredient ingredient = modelMapper.map(dtos, Ingredient.class);
        ingredient.setId(id);
        ingredient.validate("name", ingredient.getName(), (name)-> this.ingredientWriteRepository.exists(name));

        this.ingredientWriteRepository.update(ingredient);
        logger.info(this.serializeObject(ingredient, "updated"));
    }

    @Override
    public void delete(UUID id) {

        Ingredient ingredient = this.findById(id);
        this.ingredientWriteRepository.delete(ingredient);
        logger.info(this.serializeObject(ingredient, "deleted"));
    }

    @Override
    public List<IngredientProjection> getAll(String name, int page, int size) {
        return this.ingredientReadRepository.getAll(name, page, size);
    }

    private String serializeObject(Ingredient ingredient, String messege){
        StringBuilder stringBuilder = new StringBuilder("Ingredient {id: ").append(ingredient.getId())
                                                                           .append(", name: ")
                                                                           .append(ingredient.getName())
                                                                           .append(", price: ")
                                                                           .append(ingredient.getPrice())
                                                                           .append("} ")
                                                                           .append(messege)
                                                                           .append(" succesfully.");
        return stringBuilder.toString();
    }
}
