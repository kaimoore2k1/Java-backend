package com.senshop.backend.repository;

import com.senshop.backend.model.Blog;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends MongoRepository<Blog, String> {


    Blog findBySlug(String slug);

}
