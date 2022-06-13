package com.senshop.backend.controller;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import com.senshop.backend.model.Blog;
import com.senshop.backend.repository.BlogRepository;

@Controller
public class BlogController {
    @Autowired
    BlogRepository blogRepository;
    
    // @Autowired
    // private MongoTemplate mongoTemplate;

    @QueryMapping
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    @QueryMapping
    public List<Blog> getHotBlogs() {
        Sort sort = Sort.by(Sort.Direction.DESC, "like");

        return blogRepository.findAll(sort);
    }

   


}
