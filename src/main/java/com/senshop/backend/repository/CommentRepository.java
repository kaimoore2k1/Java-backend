package com.senshop.backend.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.senshop.backend.model.Comment;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    @Query("{'idProduct': ?0}")
    List<Comment> findByProductId(ObjectId idProduct);

    @Query("{'idBlog': ?0}")
    List<Comment> findByBlogId(ObjectId idBlog);
}
