package com.senshop.backend.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "admins")
public class Admin {
    public Admin() {
    }
    private String username;
    private String password;
    private String tokenVersion;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getTokenVersion() {
        return tokenVersion;
    }
    public void setTokenVersion(String tokenVersion) {
        this.tokenVersion = tokenVersion;
    }
    public Admin(String username, String password, String tokenVersion) {
        this.username = username;
        this.password = password;
        this.tokenVersion = tokenVersion;
    }
    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
