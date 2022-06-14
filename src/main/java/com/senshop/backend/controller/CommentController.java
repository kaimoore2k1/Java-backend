package com.senshop.backend.controller;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
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

    @QueryMapping
    public List<Comment> getCommentsByProductID(@Argument ObjectId productID) {
        return commentRepository.findByProductId(productID);
    }

    @QueryMapping
    public List<Comment> getCommentsByBlogID(@Argument ObjectId blogID) {
        return commentRepository.findByBlogId(blogID);
    }

    @MutationMapping
    public Comment createComment(@Argument String user, @Argument ObjectId idProduct, @Argument ObjectId idBlog,
            @Argument String content, @Argument Float rating) {
        Comment newComment = new Comment(user, idProduct, idBlog, content, rating);
        commentRepository.save(newComment);
        return newComment;
    }

    @MutationMapping
    public Comment updateComment(@Argument String _id, @Argument String user, @Argument ObjectId idProduct,
            @Argument ObjectId idBlog, @Argument String content, @Argument Float rating, @Argument Date date)
            throws Exception {
        Optional<Comment> OptComment = commentRepository.findById(_id);
        if (OptComment.isPresent()) {
            Comment updateComment = OptComment.get();
            if (content != null && content.length() > 0) {
                updateComment.setContent(content);
            }
            if (rating != null && rating > 0) {
                updateComment.setRating(rating);
            }
            updateComment.setDate(OffsetDateTime.now(ZoneOffset.UTC).toInstant());
            commentRepository.save(updateComment);
            return updateComment;
        }
        return null;

    }

    @MutationMapping
    public boolean deleteComment(@Argument String _id) {
        commentRepository.deleteById(_id);
        return true;
    }
}