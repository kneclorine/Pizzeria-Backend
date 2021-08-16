package com.example.demo.domain.commentDomain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.example.demo.domain.Entities;
import com.example.demo.domain.pizzaDomain.Pizza;
import com.example.demo.domain.userDomain.User;

@Entity
public class Comment extends Entities{

    @NotNull
    @Column(nullable = false)
    private String text;

    @NotNull
    @Column(nullable = false)
    private float rating;

    @NotNull
    @Column(nullable = false, columnDefinition = "char(10)")
    private String date;

    @ManyToOne
    @JoinColumn(
        name = "user_id", insertable = false,
        updatable = false, columnDefinition = "binary(16)"
        )
    private User user;

    @ManyToOne
    @JoinColumn(
        name = "pizza_id", insertable = false,
        updatable = false, columnDefinition = "binary(16)"
        )
    private Pizza pizza;
    
    public Comment() {
    }

    public Comment(String text, float rating, String date, User user, Pizza pizza){
        this.text = text;
        this.rating = rating;
        this.date = date;
        this.user = user;
        this.pizza = pizza;
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
        return this.user.getId();
    }

    public UUID getPizzaID(){
        return this.pizza.getId();
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

    public void setUser(User user) {
        this.user = user;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
}