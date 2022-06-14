package com.senshop.backend.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class ProductInput {
    @Id
    private String name;
    private int price;
    private int stock;
    private int salePrice;
    private String description;
    private ArrayList<String> categories;
    private String slugName;
    private String content;

    public ProductInput() {
    }

    public ProductInput(String name, int price, int stock, int salePrice, String description,
            ArrayList<String> categories, String slugName, String content) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.salePrice = salePrice;
        this.description = description;
        this.categories = categories;
        this.slugName = slugName;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public String getSlugName() {
        return slugName;
    }

    public String getContent() {
        return content;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public void setSlugName(String slugName) {
        this.slugName = slugName;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
