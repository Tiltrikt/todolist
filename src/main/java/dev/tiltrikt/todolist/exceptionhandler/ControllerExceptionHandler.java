package dev.tiltrikt.todolist.exceptionhandler;

import dev.tiltrikt.todolist.model.Task;
import dev.tiltrikt.todolist.response.UniverseResponse;
import dev.tiltrikt.todolist.exception.TaskNotFoundException;
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
    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<UniverseResponse<Task>> taskNotFoundException(TaskNotFoundException ex) {
        Map<String, String> errors = new TreeMap<>();
        errors.put("exception", ex.getMessage());

        UniverseResponse<Task> universeResponse = UniverseResponse.<Task>builder()
                .errors(errors)
                .success(false)
                .build();

        return new ResponseEntity<>(universeResponse, HttpStatus.BAD_REQUEST);
    }

    @NotNull
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<UniverseResponse<String>> NotValidField(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new TreeMap<>();
        for (FieldError fieldError:  ex.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        UniverseResponse<String> response = UniverseResponse.<String>builder()
                .errors(errors)
                .success(false)
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
