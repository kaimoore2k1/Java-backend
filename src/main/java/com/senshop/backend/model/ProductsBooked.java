package com.senshop.backend.model;

public class ProductsBooked {
    private String name;
    private String quantity;
    private String date;

    public ProductsBooked(String name, String quantity, String date) {
        this.name = name;
        this.quantity = quantity;
        this.date = date;
    }

    public ProductsBooked() {
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
