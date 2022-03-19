package com.example.starenglish.model;

import java.time.ZonedDateTime;

public class Post {
    private String _id;
    private String title, description, content;
    private String createdAt;

    public Post(String _id, String title, String description, String content, String createdAt) {
        this._id = _id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.createdAt = createdAt;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
