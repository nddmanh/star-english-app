package com.example.starenglish.model;

import com.google.gson.annotations.SerializedName;

public class UpdateScoreUserResponse {
    private int statusCode;

    private String message;

    @SerializedName("data")
    private UpdateScoreUserRequest updateScoreUserRequest;

    public UpdateScoreUserResponse(int statusCode, String message, UpdateScoreUserRequest updateScoreUserRequest) {
        this.statusCode = statusCode;
        this.message = message;
        this.updateScoreUserRequest = updateScoreUserRequest;
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

    public UpdateScoreUserRequest getUpdateScoreUserRequest() {
        return updateScoreUserRequest;
    }

    public void setUpdateScoreUserRequest(UpdateScoreUserRequest updateScoreUserRequest) {
        this.updateScoreUserRequest = updateScoreUserRequest;
    }
}
