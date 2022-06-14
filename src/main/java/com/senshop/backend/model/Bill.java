package com.senshop.backend.model;

import java.util.List;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bills")
public class Bill {
    @Id
    private String _id;
    private List<BillProduct> products;
    private String firstName;
    private String lastName;
    private String address;
    private String numberPhone;
    private int total;
    private int amount;
    private String date;
    private String paymentMethod;

    public Bill(List<BillProduct> products, String firstName, String lastName, String address,
            String numberPhone, int total, int amount, String date, String paymentMethod) {
        
        this.products = products;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.numberPhone = numberPhone;
        this.total = total;
        this.amount = amount;
        this.date = date;
        this.paymentMethod = paymentMethod;
    }

    public Bill() {
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setProducts(List<BillProduct> products) {
        this.products = products;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String get_id() {
        return _id;
    }

    public List<BillProduct> getProducts() {
        return products;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public int getTotal() {
        return total;
    }

    public int getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}
