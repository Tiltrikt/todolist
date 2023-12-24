package dev.tiltrikt.todolist.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
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
    public static TodolistResponse<String> ok() {

        return new TodolistResponse<>();
    }

    @NotNull
    public static <T> TodolistResponse<T> ok(
            @NotNull T taskList) {

        return TodolistResponse.<T>builder()
                .payload(taskList)
                .build();
    }

    @NotNull
    public static TodolistResponse<String> error(
            @NotNull Map<String, String> errors) {

        return TodolistResponse.<String>builder()
                .errors(errors)
                .success(false)
                .build();

    }
}