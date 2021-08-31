package com.example.demo.domain.commentDomain;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.example.demo.core.EntityBase;
import com.example.demo.domain.pizzaDomain.Pizza;
import com.example.demo.domain.userDomain.User;

@Entity
public class Comment extends EntityBase{

    @NotNull
    @Column(columnDefinition = "text",nullable = false)
    private String text;

    @NotNull
    @Column(nullable = false, precision = 3, scale = 1)
    @Digits(integer = 3, fraction = 1)
    private BigDecimal rating;

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

    public Comment(String text, BigDecimal rating, String date, User user, Pizza pizza){
        this.text = text;
        this.rating = rating;
        this.date = date;
        this.user = user;
        this.pizza = pizza;
    }

    public String getText(){
        return this.text;
    }

    public BigDecimal getRating(){
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

    public void setPunctuation(BigDecimal rating){
        this.rating = rating;
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