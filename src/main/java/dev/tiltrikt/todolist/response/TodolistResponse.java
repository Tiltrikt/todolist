package dev.tiltrikt.todolist.response;

import dev.tiltrikt.todolist.model.Task;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TodolistResponse<T> {

    T payload;

    Map<String, String> errors;

    @Builder.Default
    Date timestamp = new Date();

    @Builder.Default
    boolean success = true;

    @NotNull
    public static ResponseEntity<TodolistResponse<String>> ok(@NotNull HttpStatus httpStatus) {

        return new ResponseEntity<>(new TodolistResponse<>(), httpStatus);
    }

    @NotNull
    public static ResponseEntity<TodolistResponse<List<Task>>> ok(
            @NotNull List<Task> taskList,
            @NotNull HttpStatus httpStatus) {

        TodolistResponse<List<Task>> response = TodolistResponse.<List<Task>>builder()
                .payload(taskList)
                .build();

        return new ResponseEntity<>(response, httpStatus);
    }

    @NotNull
    public static ResponseEntity<TodolistResponse<String>> error(
            @NotNull Map<String, String> errors,
            @NotNull HttpStatus httpStatus) {

        TodolistResponse<String> response = TodolistResponse.<String>builder()
                .errors(errors)
                .success(false)
                .build();

        return new ResponseEntity<>(response, httpStatus);
    }
}