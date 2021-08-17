package com.example.demo.domain.userDomain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.example.demo.application.userApplication.UserFullValidation;
import com.example.demo.domain.Entities;
import com.example.demo.domain.commentDomain.Comment;


@Entity
public class User extends Entities{


    @NotNull @Size(min=2, max=255)
    @Column(nullable = false)
    private String name;

    @NotNull @Size(min=2, max=255)
    @Column(nullable = false)
    private String lastName;

    @NotNull @Size(min=2, max=255) @Email(groups = {UserFullValidation.class})
    @Column(nullable = false)
    private String email;

    @NotNull @Size(min=2, max=255)
    @Column(nullable = false)
    private String password;

    @NotNull @Size(min=2, max=255)
    @OneToMany(mappedBy = "user")
    private Set<Comment> comments = new HashSet<>();

    public User() {
    }

    public User(String name, String lastName, String email, String password){
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getName(){
        return this.name;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public Set<Comment> getComments(){
        return this.comments;
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }

    public void removeComment(Comment comment){
        this.comments.remove(comment);
    }
}
