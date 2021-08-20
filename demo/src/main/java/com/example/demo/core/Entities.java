package com.example.demo.core;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.ConstraintViolationException;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public @Getter @Setter abstract class Entities {
    
    @Id
    @Type(type = "uuid-binary")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    @Column(columnDefinition = "binary(16)")
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
