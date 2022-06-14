package com.senshop.backend.model;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "blogs")
public class Blog {
    @Id
    private String _id;
    private String title;
    private String description;
    private String content;
    @CreatedDate
    private Instant date;
    private ArrayList<String> like;
    private Image image;
    private int share;
    private String author;
    private String category;
    private String slug;
    private List<Comment> comments;

    public Blog() {
    }

    public Blog(String _id, String title, String description, String content, Instant date, ArrayList<String> like,
            Image image, int share, String author, String category, String slug) {
        this._id = _id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.date = date;
        this.like = like;
        this.image = image;
        this.share = share;
        this.author = author;
        this.category = category;
        this.slug = slug;
    }

    public Blog(String title, String image, String author, String category, String description,
            String content, String slug) {
        this.title = title;
        this.image = new Image(image);
        this.author = author;
        this.category = category;
        this.description = description;
        this.content = content;
        this.slug = slug;
        this.like = new ArrayList<String>();
        this.share = 0;
        this.date = OffsetDateTime.now(ZoneOffset.UTC).toInstant();

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

    public Instant getDate() {
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

    public String getCategory() {
        return category;
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

    public void setDate(Instant date) {
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

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public ArrayList<String> getLike() {
        return like;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setLike(ArrayList<String> like) {
        this.like = like;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}
