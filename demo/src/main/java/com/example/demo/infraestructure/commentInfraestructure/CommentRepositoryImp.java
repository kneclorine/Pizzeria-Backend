package com.example.demo.infraestructure.commentInfraestructure;

import java.util.List;
import java.util.UUID;

import com.example.demo.domain.commentDomain.Comment;
import com.example.demo.domain.repositoryDomain.Repository;

public class CommentRepositoryImp implements Repository<Comment> {

    @Override
    public void add(Comment type) {
    }

    @Override
    public void update(Comment type) {
    }

    @Override
    public void delete(Comment type) {
    }

    @Override
    public Comment get(UUID id) {
        return null;
    }

    @Override
    public List<Comment> getAll() {
        return null;
    }
    
}
