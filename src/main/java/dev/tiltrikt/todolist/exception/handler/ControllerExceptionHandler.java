package dev.tiltrikt.todolist.exception.handler;

import dev.tiltrikt.todolist.exception.TodolistException;
import dev.tiltrikt.todolist.response.TodolistResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.*;

@RestControllerAdvice
public class ControllerExceptionHandler extends DefaultHandlerExceptionResolver {

    @NotNull
    @ExceptionHandler({TodolistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public TodolistResponse<String> todolistException(TodolistException ex) {

        Map<String, String> errors = new TreeMap<>();
        errors.put("exception", ex.getMessage());

        return TodolistResponse.error(errors);
    }

    @NotNull
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public TodolistResponse<String> notValidField(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new TreeMap<>();
        for (FieldError fieldError : ex.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return TodolistResponse.error(errors);
    }

    @NotNull
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public TodolistResponse<String> notValidField() {

        Map<String, String> errors = new TreeMap<>();
        errors.put("exception", "invalid request body");

        return TodolistResponse.error(errors);
    }

    @NotNull
    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public TodolistResponse<String> noResourceFound() {

        Map<String, String> errors = new TreeMap<>();
        errors.put("exception", "no resource on this path");

        return TodolistResponse.error(errors);
    }

    @NotNull
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public TodolistResponse<String> internalServerError(Exception exception) {
        logger.info("exception: " + exception.getClass() +
                " with message: " + exception.getMessage());

        Map<String, String> errors = new TreeMap<>();
        errors.put("exception", "Internal server error");

        return TodolistResponse.error(errors);
    }
}
