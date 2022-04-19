package com.example.starenglish.model;

public class DataUserLeaderboard {
    private String _id;
    private String fullname;
    private int score;

    public DataUserLeaderboard(String _id, String fullname, int score) {
        this._id = _id;
        this.fullname = fullname;
        this.score = score;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
