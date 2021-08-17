package com.example.demo.infraestructure.commentInfraestructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.domain.commentDomain.Comment;
import com.example.demo.domain.commentDomain.CommentRepository;
import com.example.demo.domain.pizzaDomain.Pizza;
import com.example.demo.domain.userDomain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

public class CommentRepositoryImp implements CommentRepository{
 
    private CommentJPARepository commentJPARepository;

    @Autowired
    public CommentRepositoryImp(final CommentJPARepository commentJPARepository){
        this.commentJPARepository = commentJPARepository;
    }

    @Override
    public void add(Comment comment) {
        this.commentJPARepository.save(comment);
    }

    @Override
    public void update(Comment comment) {
        this.commentJPARepository.save(comment);
    }

    @Override
    public void delete(Comment comment) {
        this.commentJPARepository.delete(comment);
    }

    @Override
    public Optional<Comment> get(UUID id) {
        return this.commentJPARepository.findById(id);
    }

    @Override
    public List<Comment> getAll(String date, User user, Pizza pizza, int page, int size) {
        return this.commentJPARepository.findByCriteria(
            date, user, pizza,
            PageRequest.of(page, size)
            );
    }
}
