package com.example.demo.dto;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class TodoResponseNoTodo {
    private String id;
    private String name;

    public TodoResponseNoTodo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
