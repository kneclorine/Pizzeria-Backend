package com.example.demo.infraestructure.commentInfraestructure;

import java.util.List;
import java.util.UUID;

import com.example.demo.domain.commentDomain.Comment;
import com.example.demo.domain.pizzaDomain.Pizza;
import com.example.demo.domain.userDomain.User;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CommentJPARepository extends CrudRepository<Comment, UUID>{
    
    Comment findByDate(@Param("date") String date);
    Comment findByUser(@Param("user_id") User user);
    Comment findByPizza(@Param("pizza_id") Pizza pizza);

    @Query("""
            SELECT c FROM Comment c WHERE
            (:date IS NULL OR c.date LIKE :date) AND
            (:user IS NULL OR c.user LIKE :user) AND
            (:pizza IS NULL OR c.pizza LIKE :pizza)
            """)
    List<Comment> findByCriteria(
        @Param("date") String date, @Param("user") User user,
        @Param("pizza_id") Pizza pizza, Pageable pageable);
}
