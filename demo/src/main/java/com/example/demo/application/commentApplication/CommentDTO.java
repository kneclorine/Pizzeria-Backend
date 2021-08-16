package com.example.demo.application.commentApplication;

import java.io.Serializable;
import java.util.UUID;

public class CommentDTO implements Serializable{

    public UUID id;
    public String text;
    public float rating;
    public String date;
    public UUID userID;
    public UUID pizzaID;
}
