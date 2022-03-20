package com.example.starenglish.model;

import com.google.gson.annotations.SerializedName;

public class CurrentUserResponse {
    private int statusCode;

    private String message;

    @SerializedName("data")
    private DataCurrentUser dataCurrentUser;

    public CurrentUserResponse(int statusCode, String message, DataCurrentUser dataCurrentUser) {
        this.statusCode = statusCode;
        this.message = message;
        this.dataCurrentUser = dataCurrentUser;
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

    public DataCurrentUser getDataCurrentUser() {
        return dataCurrentUser;
    }

    public void setDataCurrentUser(DataCurrentUser dataCurrentUser) {
        this.dataCurrentUser = dataCurrentUser;
    }
}
