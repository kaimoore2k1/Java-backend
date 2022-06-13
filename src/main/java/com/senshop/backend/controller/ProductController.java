package com.senshop.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;

import com.senshop.backend.model.Product;
import com.senshop.backend.repository.ProductRepository;

@Controller
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @QueryMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @QueryMapping
    public Product getProductByName(@Argument String slugName) {
        // System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
        // System.out.println("slugName: " + slugName);
        // System.out.println("productRepository: " + productRepository.findBySlugName(slugName));
        return productRepository.findBySlugName(slugName);
    }

    @QueryMapping
    public List<Product> getAllProductsByCategory(@Argument String categories) {
        String[] category = { categories };

        Aggregation agg = Aggregation.newAggregation(Aggregation.match(Criteria.where("categories").in(category)),
                Aggregation.lookup("comments", "_id", "productId", "comments"));

        AggregationResults<Product> results = mongoTemplate.aggregate(agg, "products", Product.class);
        List<Product> mappedResult = results.getMappedResults();
        // System.out.println("mappedResult: " + mappedResult);

        return mappedResult;

    }

}
