package com.example.demo.advisors;


import com.example.demo.exception.NoTodoFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.NOT_FOUND)
public class GlobalControllerAdvice {

    @ExceptionHandler({NoTodoFoundException.class})
    public ErrorResponse handlerCompanyNotFound(Exception exception){
        return new ErrorResponse(404,"Entity Not Found");
    }
}