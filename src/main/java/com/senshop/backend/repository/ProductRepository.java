package com.senshop.backend.repository;
import com.senshop.backend.model.*;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String>{
    @Query("{'categories': ?0}")
    List<Product> findByCategory(String categories);
}
