package com.example.starenglish.model;

public class DataLogin {
    private String accessToken;

    public DataLogin(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
