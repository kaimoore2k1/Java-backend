package com.senshop.backend.repository;
import com.senshop.backend.model.*;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface  AccountRepository extends MongoRepository<Account, String> {
    @Query("{'username': ?0}")
    List<Account> findByUsername(String username);
}
