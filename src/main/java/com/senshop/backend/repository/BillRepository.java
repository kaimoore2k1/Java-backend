package com.senshop.backend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.senshop.backend.model.Bill;
import com.senshop.backend.model.User;

@Repository
public interface BillRepository extends MongoRepository<Bill, String>{
    
}
