package com.senshop.backend.model;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "blogs")
public class Blog {
    @Id
    private String _id;
    private String title;
    private String description;
    private String content;
    private Date date;
    private ArrayList<String> like;
    private Image image;
    private int share;
    private String author;
    private ArrayList<String> categories;
    private String slug;

    public Blog() {
    }
    public Blog(String _id, String title, String description, String content, Date date, ArrayList<String> like,
            Image image, int share, String author, ArrayList<String> categories, String slug) {
        this._id = _id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.date = date;
        this.like = like;
        this.image = image;
        this.share = share;
        this.author = author;
        this.categories = categories;
        this.slug = slug;
    }

    public String get_id() {
        return _id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

    public ArrayList<String> getlike() {
        return like;
    }

    public Image getImage() {
        return image;
    }

    public int getShare() {
        return share;
    }

    public String getAuthor() {
        return author;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public String getSlug() {
        return slug;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setlike(ArrayList<String> like) {
        this.like = like;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

}
