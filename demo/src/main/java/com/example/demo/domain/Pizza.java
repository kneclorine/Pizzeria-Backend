package com.example.demo.domain;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import java.util.Iterator;

@Entity
public class Pizza extends Entities{

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    @Transient
    private String url;

    @NotNull
    @ManyToMany
    @JoinTable(
        name = "pizza_ingredient",
        joinColumns = @JoinColumn(name = "pizza_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id")
    )
    private final Set<Ingredient> ingredients = new HashSet<Ingredient>();

    @NotNull
    @OneToMany(mappedBy = "pizza")
    private final Set<Comment> comments = new HashSet<Comment>();

    

    @Transient
    private BigDecimal price;

    public Pizza(String name, String url){
        this.name = name;
        this.url = url;
    }

    public String getName(){
        return this.name;
    }

    public String getURL(){
        return this.url;
    }

    public BigDecimal getPrice(){
        BigDecimal sumPrice = new BigDecimal(0);
        Iterator<Ingredient> it = ingredients.iterator();

        while(it.hasNext()){
            sumPrice.add(it.next().getPrice());
        }
        this.price = sumPrice.multiply(new BigDecimal(1.2));
        return  this.price;
    }

    public Set<Ingredient> getIngredients(){
        return this.ingredients;
    }

    public Set<Comment> getComments(){
        return this.comments;
    }

    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }

    public void removeIngredient(Ingredient ingredient){
        this.ingredients.remove(ingredient);
    }

    public void removeComment(Comment comment){
        this.comments.remove(comment);
    }

    public void setName(String name){
        this.name = name;
    }    

    public void setURL(String url){
        this.url = url;
    }
}