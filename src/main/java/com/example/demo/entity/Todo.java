package com.example.demo.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.persistence.Id;

@Document
public class Todo {
    @MongoId(FieldType.OBJECT_ID)
    @Id
    private String id;
    private String content;
    private String status;

    public Todo(){

    };

    public Todo(String content, String status) {
        this.content = content;
        this.status = status;
    }

    public String getId() {
        return id;
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
