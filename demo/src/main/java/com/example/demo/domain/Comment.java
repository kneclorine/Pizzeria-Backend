package com.example.demo.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Comment extends Entities{

    @NotNull
    @Column(nullable = false)
    private String text;

    @NotNull
    @Column(nullable = false)
    private float rating;

    @NotNull
    @Column(nullable = false)
    private String date;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private UUID userID;

    @NotNull
    @Column(name = "pizza_id", nullable = false)
    private UUID pizzaID;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "pizza_id", insertable = false, updatable = false)
    private Pizza pizza;

    public Comment(String text, float rating, String date, UUID userID, UUID pizzaID){
        this.text = text;
        this.rating = rating;
        this.date = date;
        this.userID = userID;
        this.pizzaID = pizzaID;
    }

    public String getText(){
        return this.text;
    }

    public float getRating(){
        return this.rating;
    }

    public String getDate(){
        return this.date;
    }

    public UUID getUserID(){
        return this.userID;
    }

    public UUID getPizzaID(){
        return this.pizzaID;
    }

    public void setText(String text){
        this.text = text;
    }

    public void setPunctuation(float punctuation){
        this.rating = punctuation;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUser(UUID userID) {
        this.userID = userID;
    }

    public void setPizza(UUID pizzaID) {
        this.pizzaID = pizzaID;
    }
}