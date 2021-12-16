package com.example.demo.dto;

public class TodoRequest {
    private String content;
    private String status;

    public TodoRequest(String content, String status) {
        this.content = content;
    }
    public TodoRequest() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
