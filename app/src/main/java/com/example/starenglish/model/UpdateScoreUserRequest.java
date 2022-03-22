package com.example.starenglish.model;

public class UpdateScoreUserRequest {
    private int score;

    public UpdateScoreUserRequest(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
