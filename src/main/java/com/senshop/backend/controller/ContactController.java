package com.senshop.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
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

    @MutationMapping
    public Contact createContact(@Argument String name, @Argument String mail, @Argument String content) {
        Contact newContact = new Contact(name, mail, content);
        contactRepository.save(newContact);
        return newContact;
    }

    @MutationMapping
    public boolean deleteContactById(@Argument String _id) {
        contactRepository.deleteById(_id);
        return true;
    }
}
