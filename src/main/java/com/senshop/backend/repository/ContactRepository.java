package com.senshop.backend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.senshop.backend.model.Contact;

@Repository
public interface ContactRepository extends MongoRepository<Contact, String> {
    @Query("{}")
    List<Contact> findAllContacts();
}
