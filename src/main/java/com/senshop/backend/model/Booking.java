package com.senshop.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bookings")
public class Booking {
    @Id
    private String _id;
    private String name;
    private String number;
    private String pet;
    private String service;
    private String time;
    private String dateTime;
    private String content;

    public Booking(String name, String number, String pet, String service, String time, String dateTime,
            String content) {
        this.name = name;
        this.number = number;
        this.pet = pet;
        this.service = service;
        this.time = time;
        this.dateTime = dateTime;
        this.content = content;
    }

    public Booking() {
    }

    public Booking(String _id, String name, String number, String pet, String service, String time, String dateTime,
            String content) {
        this._id = _id;
        this.name = name;
        this.number = number;
        this.pet = pet;
        this.service = service;
        this.time = time;
        this.dateTime = dateTime;
        this.content = content;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getPet() {
        return pet;
    }

    public String getService() {
        return service;
    }

    public String getTime() {
        return time;
    }

    public String getDateTime() {
        return dateTime;
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

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
