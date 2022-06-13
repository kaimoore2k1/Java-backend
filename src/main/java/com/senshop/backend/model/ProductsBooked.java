package com.senshop.backend.model;

public class ProductsBooked {
    private String ID_Product;
    private String quantity;
    
    public ProductsBooked(String quantity) {
        this.quantity = quantity;
    }

   

    public String getID_Product() {
        return ID_Product;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setID_Product(String iD_Product) {
        ID_Product = iD_Product;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

   
}
