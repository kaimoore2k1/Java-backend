package com.senshop.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import com.senshop.backend.model.Booking;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {

}