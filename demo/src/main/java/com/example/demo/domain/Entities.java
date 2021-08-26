package com.example.demo.domain;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.annotations.Type;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Validated
@MappedSuperclass
public @Getter @Setter abstract class Entities {
    
    @Id
    @Type(type = "uuid-binary")
    //@Column(columnDefinition = "binary(16)")
    private UUID id;

    public void validate(){
        
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator= factory.getValidator();
        
        Set<ConstraintViolation<Entities>> violations = validator.validate(this);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }


    @Override
    public boolean equals (Object obj) {

        if (!(obj instanceof Entities)){
            return false;
        }

        Entities tmpEntity = (Entities) obj;

        return this.id.equals(tmpEntity.id);
    }

    @Override
    public int hashCode(){
        return this.id.hashCode();
    }
}
