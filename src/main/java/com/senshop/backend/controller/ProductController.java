package com.senshop.backend.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import org.springframework.stereotype.Controller;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.senshop.backend.model.Comment;
import com.senshop.backend.model.Product;
import com.senshop.backend.model.ProductInput;
import com.senshop.backend.repository.ProductRepository;
import com.senshop.backend.repository.CommentRepository;

@Controller
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Comment> getCommentsByProductID(String productID) {
        ObjectId _id = new ObjectId(productID);
        return commentRepository.findByProductId(_id);
    }

    @QueryMapping
    public List<Product> getAllProducts() {
        // get all product without comment
        List<Product> products = productRepository.findAll();

        // set comments for each product
        products.forEach(arg0 -> {
            arg0.setComments(getCommentsByProductID(arg0.get_id()));
        });
        return products;

    }

    @QueryMapping
    public Product getProductByName(@Argument String slugName) {
        // String a = "Quần áo cho chó mèo AMBABY PET 2JXF164";
        // System.out.println("slugName: " + slugName);
        // System.out.println("productRepository: " +
        // productRepository.findBySlugName(slugName));
        // String pattern = "MM/dd/yyyy HH:mm:ss";

        // // Create an instance of SimpleDateFormat used for formatting
        // // the string representation of date according to the chosen pattern
        // DateFormat df = new SimpleDateFormat(pattern);

        // // Get the today date using Calendar object.
        Date today = new Date();
        // // Using DateFormat format method we can create a string
        // // representation of a date with the defined format.
        // String todayAsString = df.format(today);

        // // Print the result!
        // System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
        // System.out.println("Today is: " + todayAsString);
        Instant instant = today.toInstant();
        OffsetDateTime odt = instant.atOffset(ZoneOffset.UTC);

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("Today is: " + odt);

        Product product = productRepository.findBySlugName(slugName);
        product.setComments(getCommentsByProductID(product.get_id()));
        return product;
    }

    @QueryMapping
    public List<Product> getAllProductsByCategory(@Argument String categories) {
        // aggergate look up not working ???

        // LookupOperation lookupOperation = LookupOperation.newLookup()
        // .from("comments")
        // .localField("_id")
        // .foreignField("productId")
        // .as("comments");

        String[] category = { categories };

        Aggregation agg = Aggregation.newAggregation(Aggregation.match(Criteria.where("categories").in(category))
        // ,
        // lookupOperation
        // Aggregation.lookup("comments", "_id", "productId", "comments")
        );

        List<Product> results = mongoTemplate.aggregate(agg, "products", Product.class).getMappedResults();

        results.forEach(arg0 -> {
            arg0.setComments(getCommentsByProductID(arg0.get_id()));
        });

        return results;
    }

    @QueryMapping
    public List<Product> getAllProductBySearch(@Argument String valueSearch) {

        // $match: {$or: [{ name: { $regex: valueSearch, $options: 'i' } }, {
        // description: { $regex: valueSearch, $options: 'i' } }] }
        Aggregation agg = Aggregation.newAggregation(Aggregation.match(
                Criteria.where("name").regex(valueSearch, "i")
                        .orOperator(Criteria.where("description").regex(valueSearch, "i")))
        // ,
        // Aggregation.lookup("comments", "_id", "productId", "comments")
        );

        List<Product> results = mongoTemplate.aggregate(agg, "products", Product.class).getMappedResults();

        results.forEach(arg0 -> {
            arg0.setComments(getCommentsByProductID(arg0.get_id()));
        });

        return results;
    }

    @QueryMapping
    public List<Product> getProductById(@Argument String _id) {

        Aggregation agg = Aggregation.newAggregation(Aggregation.match(Criteria.where("_id").is(_id)));

        List<Product> results = mongoTemplate.aggregate(agg, "products", Product.class).getMappedResults();

        results.forEach(arg0 -> {
            arg0.setComments(getCommentsByProductID(arg0.get_id()));
        });

        return results;
    }

    @MutationMapping
    public Product deleteProductByName(@Argument String name) {

        return productRepository.deleteByName(name);
    }

    @MutationMapping
    public Product updateProductByName(@Argument String name, @Argument ProductInput data) {
        Query query = new Query(Criteria.where("name").is(name));
        Product _product = mongoTemplate.findOne(query, Product.class, "products");

        String dataName = data.getName();
        int dataPrice = data.getPrice();
        int dataStock = data.getStock();
        int dataSalePrice = data.getSalePrice();
        String dataDescription = data.getDescription();
        ArrayList<String> dataCategories = data.getCategories();
        String dataSlugName = data.getSlugName();
        String dataContent = data.getContent();
        // System.out.println("\n\n\n\n\n\n\n\n\n\n"+
        // productRepository.findByName(name));S
        Optional<Product> productOptional = Optional.ofNullable(_product);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();

            // name
            if (dataName != null && dataName.length() > 0)
                product.setName(dataName);
            // price
            if (dataPrice != 0)
                product.setPrice(dataPrice);
            // stock
            if (dataStock != 0)
                product.setStock(dataStock);
            // salePrice
            if (dataSalePrice != 0)
                product.setSalePrice(dataSalePrice);
            // description
            if (dataDescription != null && dataDescription.length() > 0)
                product.setDescription(dataDescription);
            // categories
            if (dataCategories != null && dataCategories.size() > 0)
                product.setCategories(dataCategories);
            // slugName
            if (dataSlugName != null && dataSlugName.length() > 0)
                product.setSlugName(dataSlugName);
            // content
            if (dataContent != null && dataContent.length() > 0)
                product.setContent(dataContent);

            return productRepository.save(product);
        } else
            return mongoTemplate.save(new Product(dataName, dataPrice, dataStock, dataSalePrice, dataDescription,
                    dataCategories, dataSlugName, dataContent), "products");

    }
}
