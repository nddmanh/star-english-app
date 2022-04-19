package com.example.starenglish.model;

public class User {

    private String username;
    private String password;
    private String fullname;
    private String school;
    private int age;
    private String role;
    private int score;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String fullname, int score) {
        this.fullname = fullname;
        this.score = score;
    }

    public User(String username, String password, String fullname, String school, int age) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.school = school;
        this.age = age;
    }

    public User(String username, String password, String fullname, String school, int age, String role, int score) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.school = school;
        this.age = age;
        this.role = role;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullname='" + fullname + '\'' +
                ", school='" + school + '\'' +
                ", age=" + age +
                ", role='" + role + '\'' +
                ", score=" + score +
                '}';
    }
}
