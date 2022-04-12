package com.example.starenglish.model;

public class LoginGoogleRequest {
    private String googleToken;

    public LoginGoogleRequest(String googleToken) {
        this.googleToken = googleToken;
    }

    public String getGoogleToken() {
        return googleToken;
    }

    public void setGoogleToken(String googleToken) {
        this.googleToken = googleToken;
    }
}
