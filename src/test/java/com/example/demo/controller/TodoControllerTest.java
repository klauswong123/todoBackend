package com.example.demo.controller;
import com.example.demo.entity.Todo;
import com.example.demo.mapper.TodoMapper;
import com.example.demo.repository.TodoRepository;
import com.example.demo.service.TodoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    TodoMapper todoMapper;
    @Autowired
    TodoService todoService;
    @Autowired
    TodoRepository todoRepository;
    @BeforeEach
    @AfterEach
    void cleanTodo(){
        todoRepository.deleteAll();
    }

    private Todo preCreateTodo(){
        return todoRepository.insert(new Todo("Apple","Done"));
    }


    @Test
    void should_get_all_todo_when_perform_get_given_() throws Exception {
        //given
        preCreateTodo();
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/todoitem"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").isString())
                .andExpect(jsonPath("$[0].content").value("Apple"));
        //then
    }

    @Test
    void should_get_todo_when_perform_get_given_id() throws Exception {
        //given
        Todo todo = preCreateTodo();
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/todoitem/{id}",todo.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.content").value("Apple"));
        //then
    }


    @Test
    void should_return_todo_when_perform_post_given_todo() throws Exception {
        //given
        String newTodo = "{\n" +
                "        \"content\": \"hello world\",\n" +
                "        \"status\": \"todo/DONE\"\n" +
                "    }";
        //when
        //then
        mockMvc.perform(post("/todoitem")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newTodo))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.content").value("hello world"));
    }

    @Test
    void should_return_changed_todo_when_perform_put_given_todo_id() throws Exception {
        //given
        Todo todo = preCreateTodo();
        String newTodo = "{\n" +
                "        \"content\": \"hello world\",\n" +
                "        \"status\": \"todo/DONE\"\n" +
                "    }";
        //when
        //then
        mockMvc.perform(put("/todoitem/{id}",todo.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(newTodo))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("hello world"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isString());
    }

    @Test
    void should_delete_todo_when_perform_delete_given_todo_and_id() throws Exception {
        //given
        Todo todo = preCreateTodo();
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/todoitem/{id}", todo.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void should_return_notfound_when_perform_get_given_todo_and_falseId() throws Exception {
        //given
        Todo todo = preCreateTodo();
        todoRepository.delete(todo);
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/todoitem/{id}",todo.getId()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
