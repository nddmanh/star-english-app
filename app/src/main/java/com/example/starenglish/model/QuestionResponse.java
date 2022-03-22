package com.example.starenglish.model;

import com.google.gson.annotations.SerializedName;

public class QuestionResponse {
    private int statusCode;

    private String message;

    @SerializedName("data")
    private DataQuestion dataQuestion;

    public QuestionResponse(int statusCode, String message, DataQuestion dataQuestion) {
        this.statusCode = statusCode;
        this.message = message;
        this.dataQuestion = dataQuestion;
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

    public DataQuestion getDataQuestion() {
        return dataQuestion;
    }

    public void setDataQuestion(DataQuestion dataQuestion) {
        this.dataQuestion = dataQuestion;
    }
}
