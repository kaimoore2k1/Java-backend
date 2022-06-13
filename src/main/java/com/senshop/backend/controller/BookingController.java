package com.senshop.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.senshop.backend.model.Booking;
import com.senshop.backend.repository.BookingRepository;

@Controller
public class BookingController {
    @Autowired
    BookingRepository bookingRepository;

    @QueryMapping
    public List<Booking> getAllBooking() {
        return bookingRepository.findAll();
    }

    @MutationMapping
    public Booking createBooking(@Argument String name, @Argument String number, @Argument String pet,
            @Argument String service, @Argument String time, @Argument String dateTime, @Argument String content) {
        Booking newBooking = new Booking(name, number, pet, service, time, dateTime, content);
        bookingRepository.save(newBooking);
        return newBooking;
    }

    @MutationMapping
    public boolean deleteBookingById(@Argument String _id) {
        bookingRepository.deleteById(_id);
        return true;
    }
}
