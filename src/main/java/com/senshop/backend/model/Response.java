package com.senshop.backend.model;

public class Response {
    public void setStatus(String status) {
        this.status = status;
    }

    public Response(String status, String message, String success, Admin adminData) {
        this.status = status;
        this.message = message;
        this.success = success;
        this.adminData = adminData;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    private String status;
    private String message;
    private String success;
    private Account accountData;
    private Admin adminData;
    private String accessToken;

    public Response(String status, String message, String success, String accessToken) {
        this.status = status;
        this.message = message;
        this.success = success;
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Response(String status, String message, String success, Account data) {
        this.status = status;
        this.message = message;
        this.success = success;
        accountData = data;
    }
    public Response(String status, String message, String success) {
        this.status = status;
        this.message = message;
        this.success = success;
    }
    public Response() {
    }



    public Account getAccountData() {
        return accountData;
    }

    public void setAccountData(Account accountData) {
        this.accountData = accountData;
    }

    public Admin getAdminData() {
        return adminData;
    }

    public void setAdminData(Admin adminData) {
        this.adminData = adminData;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getSuccess() {
        return success;
    }
}
