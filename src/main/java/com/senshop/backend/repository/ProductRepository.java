package com.senshop.backend.repository;

import com.senshop.backend.model.*;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

        // @Query("{'slugName': ?0}")
        // Product findBySlugName(String slugName);
        @Aggregation(pipeline = { "{'$match': {'slugName': ?0}}",
                        "{'$lookup':{'from':'comments','localField':'_id','foreignField':'productId','as':'comments'}}" })
        Product findBySlugName(String slugName);

        Product deleteByName(String name);

        Product findByName(String name);

        // @Query("{'name': ?0}")
        // @Update("{'$set': {'name': ?1,'price': ?2, 'salePrice': ?3 , 'stock': ?4,
        // 'description':?5 , content:?6,'slugName': ?7,'categories': ?8}}")
        // Product findOneByNameAndUpdate(String name, String _name, int price, int
        // salePrice, int stock,
        // String description, String content, String slugName, ArrayList<String>
        // categories);
        // @Aggregation(pipeline = { "{'$match': {'categories': '$in':[?0]}}",
        // "{'$lookup':{'from':'comments','localField':'_id','foreignField':'productId','as':'comments'}}"
        // })

}
