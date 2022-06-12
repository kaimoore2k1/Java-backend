package com.senshop.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "contacts")
public class Contact {
    @Id
    private String _id;
    private String name;
    private String mail;
    private String content;

    public Contact(String _id, String name, String mail, String content) {
        this._id = _id;
        this.name = name;
        this.mail = mail;
        this.content = content;
    }

    public Contact() {
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getContent() {
        return content;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
