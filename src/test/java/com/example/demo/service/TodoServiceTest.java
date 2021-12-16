package com.example.demo.service;
import com.example.demo.entity.Todo;
import com.example.demo.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class TodoServiceTest {
    @Mock
    TodoRepository todoRepository;
    @InjectMocks
    TodoService todoService;


    @Test
    void should_return_all_todo_when_find_all_given_todo() {
        //given
        List<Todo> companies = new ArrayList<>();
        companies.add(new Todo("Spring","Done"));
        given(todoRepository.findAll())
                .willReturn(companies);
        //when
        List<Todo> actual = todoService.findAll();
        //then
        assertEquals(companies.size(), actual.size());
    }
    @Test
    void should_return_a_todo_when_get_todo_given_todo_id() {
        //given
        Todo todo1 = new Todo("Spring","Done");
        given(todoRepository.findById(todo1.getId()))
                .willReturn(java.util.Optional.of(todo1));
        //when
        Todo actual = todoService.getByID(todo1.getId());
        //then
        assertEquals(todo1, actual);
    }


    @Test
    void should_return_update_todo_when_perform_put_given_todo_id() throws Exception {
        //given
        Todo todo = new Todo("OOCL3","Done");
        Todo updatedCompany = new Todo("OOCLL","Done");
        todo.setContent(updatedCompany.getContent());
        given(todoRepository.findById(todo.getId()))
                .willReturn(java.util.Optional.of(todo));
        given(todoRepository.save(updatedCompany))
                .willReturn(todo);
        //when
        Todo actual = todoService.edit(todo.getId(), updatedCompany);
        System.out.println(actual.getContent());
        //then
        assertEquals(updatedCompany.getId(), actual.getId());

    }


    @Test
    void should_delete_todo_when_perform_delete_given_todo_and_id() throws Exception {
        //given
        Todo company = new Todo("Spring","Done");
        willDoNothing().given(todoRepository).deleteById(company.getId());
        //when
        todoService.delete(company.getId());
        //then
        verify(todoRepository).deleteById(company.getId());
        assertEquals(0, todoRepository.findAll().size());
    }

}