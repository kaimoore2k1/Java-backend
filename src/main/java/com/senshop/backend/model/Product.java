package com.senshop.backend.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "accounts")
public class Product {
    @Id
    private String _id;
    private String name;
    private int price;
    private int stock;
    private int salePrice;
    private String description;
    private int rating;
    private Variant variant;
    private ArrayList<Image> images;
    private ArrayList<String> categories;
    private String slugName;
    private String content;
    
    public Product() {
    }

    public Product(String _id, String name, int price, int stock, int salePrice, String description, int rating,
            Variant variant, ArrayList<Image> images, ArrayList<String> categories, String slugName, String content) {
        this._id = _id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.salePrice = salePrice;
        this.description = description;
        this.rating = rating;
        this.variant = variant;
        this.images = images;
        this.categories = categories;
        this.slugName = slugName;
        this.content = content;
    }

    public String get_id() {
        return _id;
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

    public int getRating() {
        return rating;
    }

    public Variant getVariant() {
        return variant;
    }

    public ArrayList<Image> getImages() {
        return images;
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

    public void set_id(String _id) {
        this._id = _id;
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

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setVariant(Variant variant) {
        this.variant = variant;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
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
