package com.example.demo.mapper;

import com.example.demo.dto.TodoRequest;
import com.example.demo.dto.TodoResponse;
import com.example.demo.dto.TodoResponseNoTodo;
import com.example.demo.entity.Todo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoMapper {
    public Todo toEntity(TodoRequest todoRequest){
        Todo todo = new Todo();
        BeanUtils.copyProperties(todoRequest, todo);
        return todo;
    }
    public TodoResponse toResponse(Todo todo) {
        TodoResponse todoResponse = new TodoResponse();
        BeanUtils.copyProperties(todo, todoResponse);
        return todoResponse;
    }

//    public TodoResponseNoTodo toResponseNoEmployee(Todo todo){
//        TodoResponseNoTodo todoResponseNoTodo = new TodoResponseNoTodo();
//        BeanUtils.copyProperties(todo, todoResponseNoTodo);
//        return todoResponseNoTodo;
//    }

}
