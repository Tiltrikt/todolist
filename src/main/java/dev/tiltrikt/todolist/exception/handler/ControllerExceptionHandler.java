package dev.tiltrikt.todolist.exception.handler;

import dev.tiltrikt.todolist.exception.TodolistException;
import dev.tiltrikt.todolist.model.Task;
import dev.tiltrikt.todolist.response.TodolistResponse;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @NotNull
    @ExceptionHandler(TodolistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<TodolistResponse<String>> todolistException(TodolistException ex) {

        Map<String, String> errors = new TreeMap<>();
        errors.put("exception", ex.getMessage());

        return TodolistResponse.error(errors, HttpStatus.BAD_REQUEST);
    }

    @NotNull
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<TodolistResponse<String>> notValidField(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new TreeMap<>();
        for (FieldError fieldError:  ex.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return TodolistResponse.error(errors, HttpStatus.BAD_REQUEST);
    }

    @NotNull
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<TodolistResponse<String>> internalServerError() {

        Map<String, String> errors = new TreeMap<>();
        errors.put("exception", "Internal server error");

        return TodolistResponse.error(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
