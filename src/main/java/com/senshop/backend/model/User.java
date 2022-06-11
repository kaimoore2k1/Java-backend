package com.senshop.backend.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    @Id
    private String _id;
    private String username;
    private String firstName;
    private String lastName;
    private String country;
    private String address;
    private String city;
    private String numberPhone;
    private String sex;
    private String birthday;
    private String email;
    private ArrayList<ProductsBooked> productsBooked;
    private String dateCreate;
    private String avatarUrl;

    public User(String _id, String username, String firstName, String lastName, String country, String address,
            String city, String numberPhone, String sex, String birthday, String email,
            ArrayList<ProductsBooked> productsBooked, String dateCreate, String avatarUrl) {
        this._id = _id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.address = address;
        this.city = city;
        this.numberPhone = numberPhone;
        this.sex = sex;
        this.birthday = birthday;
        this.email = email;
        this.productsBooked = productsBooked;
        this.dateCreate = dateCreate;
        this.avatarUrl = avatarUrl;
    }

    public User() {
    }

    public String get_id() {
        return _id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCountry() {
        return country;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public String getSex() {
        return sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<ProductsBooked> getProductsBooked() {
        return productsBooked;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProductsBooked(ArrayList<ProductsBooked> productsBooked) {
        this.productsBooked = productsBooked;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
