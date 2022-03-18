package com.example.starenglish.model;

public class SignupRequest {
    private String username, password, fullname, school;
    private int age;

    public SignupRequest(String username, String password, String fullname, String school, int age) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.school = school;
        this.age = age;
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
}
