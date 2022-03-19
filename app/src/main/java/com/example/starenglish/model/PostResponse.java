package com.example.starenglish.model;

import com.google.gson.annotations.SerializedName;

public class PostResponse {
    private int statusCode;

    private String message;

    @SerializedName("data")
    private DataPost dataPost;

    public PostResponse(int statusCode, String message, DataPost dataPost) {
        this.statusCode = statusCode;
        this.message = message;
        this.dataPost = dataPost;
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

    public DataPost getDataPost() {
        return dataPost;
    }

    public void setDataPost(DataPost dataPost) {
        this.dataPost = dataPost;
    }
}
