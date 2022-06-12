package com.senshop.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.senshop.backend.model.Product;
import com.senshop.backend.repository.ProductRepository;

@Controller
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @QueryMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
