package com.example.starenglish.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    private int statusCode;

    private String message;

    @SerializedName("data")
    private DataLogin dataLogin;

    public LoginResponse(int statusCode, String message, DataLogin dataLogin) {
        this.statusCode = statusCode;
        this.message = message;
        this.dataLogin = dataLogin;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataLogin getDataLogin() {
        return dataLogin;
    }

    public void setDataLogin(DataLogin dataLogin) {
        this.dataLogin = dataLogin;
    }

    @Override
    public String toString() {
        return "Login{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", dataLogin=" + dataLogin +
                '}';
    }
}
