package com.example.demo.application.commentApplication;

import java.io.Serializable;
import java.util.UUID;

import com.example.demo.domain.pizzaDomain.Pizza;
import com.example.demo.domain.userDomain.User;

public class CommentDTO implements Serializable{

    public UUID id;
    public String text;
    public float rating;
    public String date;
    public User user;
    public Pizza pizza;
}
