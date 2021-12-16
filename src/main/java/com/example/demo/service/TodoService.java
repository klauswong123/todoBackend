package com.example.demo.service;

import com.example.demo.entity.Todo;
import com.example.demo.exception.NoTodoFoundException;
import com.example.demo.mapper.TodoMapper;
import com.example.demo.repository.TodoRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TodoService {
    private TodoRepository todoRepository;
    private TodoMapper todoMapper;

    public TodoService(TodoRepository todoRepository, TodoMapper todoMapper){
        this.todoRepository = todoRepository;
        this.todoMapper = todoMapper;
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Todo edit(String id, Todo todo){
        Todo targetTodo = getByID(id);
        if(todo.getContent()!=null) targetTodo.setContent(todo.getContent());
        if(todo.getStatus()!=null) targetTodo.setStatus(todo.getStatus());
        todoRepository.save(targetTodo);
        return targetTodo;
    }

    public Todo getByID(String id) {
        return todoRepository.findById(id).orElseThrow(NoTodoFoundException::new);
    }


    public Todo create(Todo todo) {
        return todoRepository.insert(todo);
    }

    public List<Todo> getByPage(int page, int pageSize) {
        return todoRepository.findAll(PageRequest.of(page-1, pageSize)).toList();
    }

    public Integer getTotalPageNumberByPagesize(int pageSize) {
        if (todoRepository.findAll().size()%pageSize==0){
            return (todoRepository.findAll().size()/pageSize);
        }
        return (todoRepository.findAll().size()/pageSize)+1;
    }

    public void delete(String id) {
        todoRepository.deleteById(id);
    }
}
