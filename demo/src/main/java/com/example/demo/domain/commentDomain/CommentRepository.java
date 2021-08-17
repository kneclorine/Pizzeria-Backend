package com.example.demo.domain.commentDomain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.domain.pizzaDomain.Pizza;
import com.example.demo.domain.userDomain.User;

public interface CommentRepository {

    public void add(Comment comment);
    public void update(Comment comment);
    public void delete(Comment comment);
    public Optional<Comment> get(UUID id);
    public List<Comment> getAll(String date, User user, Pizza pizza, int page, int size);
}
