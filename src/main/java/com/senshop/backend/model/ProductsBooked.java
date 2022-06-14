package com.senshop.backend.model;

public class ProductsBooked {
    private String ID_Product;
    private int quantity;
    
    public ProductsBooked(int quantity) {
        this.quantity = quantity;
    }

    public ProductsBooked(String ID_Product,int quantity) {
        this.ID_Product = ID_Product;
        this.quantity = quantity;
    }
    

    public ProductsBooked(){

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
