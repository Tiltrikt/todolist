package dev.tiltrikt.todolist.controller.v1;

import dev.tiltrikt.todolist.model.Task;
import dev.tiltrikt.todolist.request.TaskAddRequest;
import dev.tiltrikt.todolist.request.TaskChangeRequest;
import dev.tiltrikt.todolist.response.TodolistResponse;
import dev.tiltrikt.todolist.service.task.TaskService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/tasks")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskController {

    TaskService taskService;

    @NotNull
    @GetMapping()
    public ResponseEntity<TodolistResponse<List<Task>>> getAll() {
        List<Task> taskList = taskService.getAll();

        return TodolistResponse.ok(taskList, HttpStatus.OK);
    }

    @NotNull
    @GetMapping("/active")
    public ResponseEntity<TodolistResponse<List<Task>>> getActive() {
        List<Task> taskList = taskService.getByActive(true);

        return TodolistResponse.ok(taskList, HttpStatus.OK);
    }

    @NotNull
    @GetMapping("/finished")
    public ResponseEntity<TodolistResponse<List<Task>>> getFinished() {
        List<Task> taskList = taskService.getByActive(false);

        return TodolistResponse.ok(taskList, HttpStatus.OK);
    }

    @NotNull
    @PostMapping("/add")
    public ResponseEntity<TodolistResponse<String>> addTask(@Valid @RequestBody TaskAddRequest request) {
        taskService.add(request);

        return TodolistResponse.ok(HttpStatus.CREATED);
    }

    @NotNull
    @PutMapping("/update/{id}")
    public ResponseEntity<TodolistResponse<String>> updateTask(
            @PathVariable int id, @Valid @RequestBody TaskChangeRequest request) {
        taskService.update(id, request);

        return TodolistResponse.ok(HttpStatus.OK);
    }

    @NotNull
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TodolistResponse<String>> deleteTask(@PathVariable int id) {
        taskService.delete(id);

        return TodolistResponse.ok(HttpStatus.OK);
    }

}
