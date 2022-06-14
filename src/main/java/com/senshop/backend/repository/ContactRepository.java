package com.senshop.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.senshop.backend.model.Contact;

@Repository
public interface ContactRepository extends MongoRepository<Contact, String>{
    
}
