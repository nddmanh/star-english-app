package com.example.starenglish.model;

import com.google.gson.annotations.SerializedName;

public class LeaderboardResponse {
    private int statusCode;

    private String message;

    @SerializedName("data")
    private DataLeaderboard dataLeaderboard;

    public LeaderboardResponse(int statusCode, String message, DataLeaderboard dataLeaderboard) {
        this.statusCode = statusCode;
        this.message = message;
        this.dataLeaderboard = dataLeaderboard;
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

    public DataLeaderboard getDataLeaderboard() {
        return dataLeaderboard;
    }

    public void setDataLeaderboard(DataLeaderboard dataLeaderboard) {
        this.dataLeaderboard = dataLeaderboard;
    }
}
