package com.example.starenglish.model;

import java.util.List;

public class DataPost {
    private int page;
    private int limit;
    private List<Post> posts;

    public DataPost(int page, int limit, List<Post> posts) {
        this.page = page;
        this.limit = limit;
        this.posts = posts;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
