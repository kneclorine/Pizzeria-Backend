package com.example.demo.domain;

import java.util.UUID;

public class Comment extends Entity{
    private String text;
    private float rating;
    private String date;
    private UUID userID;
    private UUID pizzaID;

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
