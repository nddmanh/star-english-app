package com.example.starenglish.model;

public class DataCurrentUser {
    private String _id, email, fullname, role;
    private int score;

    public DataCurrentUser(String _id, String email, String fullname, String role, int score) {
        this._id = _id;
        this.email = email;
        this.fullname = fullname;
        this.role = role;
        this.score = score;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
