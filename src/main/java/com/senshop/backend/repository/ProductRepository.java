package com.senshop.backend.repository;

import com.senshop.backend.model.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

        // @Query("{'slugName': ?0}")
        // Product findBySlugName(String slugName);
        @Aggregation(pipeline = { "{'$match': {'slugName': ?0}}",
                        "{'$lookup':{'from':'comments','localField':'_id','foreignField':'productId','as':'comments'}}" })
        Product findBySlugName(String slugName);

        // @Aggregation(pipeline = { "{'$match': {'categories': '$in':[?0]}}",
        // "{'$lookup':{'from':'comments','localField':'_id','foreignField':'productId','as':'comments'}}"
        // })

}
