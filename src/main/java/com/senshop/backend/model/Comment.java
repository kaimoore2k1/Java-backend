package com.senshop.backend.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comments")
public class Comment {
    @Id
    private String _id;
    private String user;
    private ObjectId idProduct;
    private ObjectId idBlog;
    private String content;
    private Float rating;
    private Date date;

    public Comment(String user, ObjectId idProduct, ObjectId idBlog, String content, Float rating) {
        this.user = user;
        this.idProduct = idProduct;
        this.idBlog = idBlog;
        this.content = content;
        this.rating = rating;
    }

    public Comment(String _id, String user, ObjectId idProduct, ObjectId idBlog, String content, Float rating,
            Date date) {
        this._id = _id;
        this.user = user;
        this.idProduct = idProduct;
        this.idBlog = idBlog;
        this.content = content;
        this.rating = rating;
        this.date = date;
    }

    public Comment() {
    }

    public String get_id() {
        return _id;
    }

    public String getUser() {
        return user;
    }

    public ObjectId getIdProduct() {
        return idProduct;
    }

    public ObjectId getIdBlog() {
        return idBlog;
    }

    public String getContent() {
        return content;
    }

    public Float getRating() {
        return rating;
    }

    public Date getDate() {
        return date;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setIdProduct(ObjectId idProduct) {
        this.idProduct = idProduct;
    }

    public void setIdBlog(ObjectId idBlog) {
        this.idBlog = idBlog;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
