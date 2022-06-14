package com.senshop.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.senshop.backend.model.Contact;
import com.senshop.backend.repository.ContactRepository;

@Controller
public class ContactController {
    @Autowired
    ContactRepository contactRepository;

    @QueryMapping
    public List<Contact> getAllContact() {
        return contactRepository.findAll();
    }
}
