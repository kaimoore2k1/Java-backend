package com.senshop.backend.model;

public class BookedProductInput {
    private String ID_Product;
    private int   quantity;
    public BookedProductInput(){

    }

    public BookedProductInput(String ID_Product, int quantity){
        this.ID_Product = ID_Product;
        this.quantity = quantity;
    }

    public BookedProductInput(int quantity){
        this.quantity = quantity;
    }

    public String getID_Product() {
        return ID_Product;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setID_Product(String iD_Product) {
        ID_Product = iD_Product;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
