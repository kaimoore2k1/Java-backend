package com.senshop.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.senshop.backend.model.Comment;
import com.senshop.backend.repository.CommentRepository;

@Controller
public class CommentController {
    @Autowired
    CommentRepository commentRepository;

    @QueryMapping
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
}
