package com.example.demo.controller;

import com.example.demo.dto.TodoRequest;
import com.example.demo.dto.TodoResponse;
import com.example.demo.mapper.TodoMapper;
import com.example.demo.entity.Todo;
import com.example.demo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("todoitem")
public class TodoController {
    private TodoService todoService;
    private TodoMapper todoMapper;
    public TodoController(TodoService todoService, TodoMapper todoMapper){
        this.todoService = todoService;
        this.todoMapper = todoMapper;
    }

    @GetMapping
    public List<Todo> getTodo(){
        return todoService.findAll();
    }

    @GetMapping("/{id}")
    public TodoResponse getTodoByID(@PathVariable("id") String id) {
        return todoMapper.toResponse(todoService.getByID(id));
    }

    @GetMapping(params = {"pageSize"})
    public Integer getTotalPageNumber(@RequestParam Integer pageSize) {
        return todoService.getTotalPageNumberByPagesize(pageSize);
    }

    @GetMapping(params = {"page","pageSize"})
    public List<TodoResponse> getTodoByPage(@RequestParam Integer page, Integer pageSize){
        return todoService.getByPage(page,pageSize).stream()
                .map(todo -> todoMapper.toResponse(todo))
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Todo createTodo(@RequestBody TodoRequest todoRequest){
        return todoService.create(todoMapper.toEntity(todoRequest));
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable ("id") String id, @RequestBody TodoRequest todoRequest) {
        return todoService.edit(id, todoMapper.toEntity(todoRequest));
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable ("id") String id){
        todoService.delete(id);
    }

}
