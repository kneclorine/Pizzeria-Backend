package com.example.demo.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
public abstract class Entities {
    
    @Id
    @Type(type = "uuid-binary")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    @Column(columnDefinition = "binary(16)")
    private UUID id;

    public void generateID(UUID id){
        this.id = id;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id){
        this.id = id;
    }

    public boolean validate(UUID id){
        if(this.id != null){
            try {
                UUID.fromString(this.id.toString());
                return true;
             } catch (Exception ex) {
                return false;
             }
        }
        
        return false;
    }

    @Override
    public boolean equals (Object obj) {

        if (!(obj instanceof Entities)){
            return false;
        }

        Entities tmpEntity = (Entities) obj;

        return this.getId().equals(tmpEntity.getId());
    }

    @Override
    public int hashCode(){
        return this.getId().hashCode();
    }
}
