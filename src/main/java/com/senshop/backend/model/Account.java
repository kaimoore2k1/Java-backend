package com.senshop.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "accounts")
public class Account {
    @Id
    private String _id;
    private String username;
    private String password;
    private String email;
    private String provider;
    private String providerId;
    private String tokenVersion;

    public Account(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Account(String _id, String username, String password, String email, String provider, String providerId,
            String tokenVersion) {
        this._id = _id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.provider = provider;
        this.providerId = providerId;
        this.tokenVersion = tokenVersion;
    }

    public Account(String id, String username, String password, String email, String provider) {
        this._id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.provider = provider;
    }

    public Account() {

    }

    public String get_id() {
        return _id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getProvider() {
        return provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public String getTokenVersion() {
        return tokenVersion;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public void setTokenVersion(String tokenVersion) {
        this.tokenVersion = tokenVersion;
    }

    @Override
    public String toString() {
        return "Account [_id=" + _id + ", email=" + email + ", password=" + password + ", provider=" + provider
                + ", providerId=" + providerId + ", tokenVersion=" + tokenVersion + ", username=" + username + "]";
    }
}
