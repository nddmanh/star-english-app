package com.example.starenglish.model;

import java.util.List;

public class DataQuestion {
    private int page, limit, count;
    private List<Question> questions;

    public DataQuestion(int page, int limit, int count, List<Question> questions) {
        this.page = page;
        this.limit = limit;
        this.count = count;
        this.questions = questions;
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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
