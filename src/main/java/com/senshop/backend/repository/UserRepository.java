package com.senshop.backend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.senshop.backend.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{
    @Query("{'username': ?0}")
    List<User> findByUsername(String username);
}
