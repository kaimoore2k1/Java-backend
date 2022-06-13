package com.senshop.backend.model;

public class InputBillProduct {
    private String name;
    private int quantity;
    private int price;

    public InputBillProduct(){

    }

    public InputBillProduct(String name, int quantity, int price){
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
    
    public String getName() {
        return name;
    }
    public int getQuantity() {
        return quantity;
    }
    public int getPrice() {
        return price;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setPrice(int price) {
        this.price = price;
    }
}
