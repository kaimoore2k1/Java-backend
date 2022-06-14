package com.senshop.backend.model;

public class Response {
    public void setStatus(String status) {
        this.status = status;
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
    private Account data;

    public Response(String status, String message, String success, Account data) {
        this.status = status;
        this.message = message;
        this.success = success;
        this.data = data;
    }
    public Response(String status, String message, String success) {
        this.status = status;
        this.message = message;
        this.success = success;
    }
    public Response() {
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

    public Account getData() {
        return data;
    }

    public void setData(Account data) {
        this.data = data;
    }
}
