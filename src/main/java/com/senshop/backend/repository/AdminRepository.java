package com.senshop.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.senshop.backend.model.Admin;
import com.senshop.backend.model.Response;

public interface AdminRepository extends MongoRepository<Admin, String>{
    Admin findByUsername(String username);
}
