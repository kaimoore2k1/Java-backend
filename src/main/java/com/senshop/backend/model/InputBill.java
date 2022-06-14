package com.senshop.backend.model;

import java.util.List;

public class InputBill {
    private List<InputBillProduct> products;
    private String firstName;
    private String lastName;
    private String address;
    private String numberPhone;
    private int total;
    private int amount;
    private String date;
    private String paymentMethod;

    

    public InputBill(List<InputBillProduct> products, String firstName, String lastName,String address,String numberPhone,int total,int amount,String date,String paymentMethod){
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

    public InputBill(){

    }

    public List<InputBillProduct> getProducts() {
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
    public void setProducts(List<InputBillProduct> products) {
        this.products = products;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public void setTotal(int total) {
        this.total = total;
    }
}
