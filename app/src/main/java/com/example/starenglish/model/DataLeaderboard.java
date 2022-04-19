package com.example.starenglish.model;

import java.util.List;

public class DataLeaderboard {
    private int page;
    private int limit;
    private int totalPages;
    private List<DataUserLeaderboard> users;

    public DataLeaderboard(int page, int limit, int totalPages, List<DataUserLeaderboard> users) {
        this.page = page;
        this.limit = limit;
        this.totalPages = totalPages;
        this.users = users;
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

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<DataUserLeaderboard> getUsers() {
        return users;
    }

    public void setUsers(List<DataUserLeaderboard> users) {
        this.users = users;
    }
}
