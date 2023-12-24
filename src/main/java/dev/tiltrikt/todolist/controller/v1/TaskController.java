package dev.tiltrikt.todolist.controller.v1;

import dev.tiltrikt.todolist.configuration.DefaultUserConfiguration;
import dev.tiltrikt.todolist.model.Task;
import dev.tiltrikt.todolist.dto.task.TaskAddRequest;
import dev.tiltrikt.todolist.dto.task.TaskChangeRequest;
import dev.tiltrikt.todolist.response.TodolistResponse;
import dev.tiltrikt.todolist.service.task.TaskService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("taskControllerV1")
@RequiredArgsConstructor
@RequestMapping("/v1/tasks")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskController {

    @Qualifier("taskServiceImpl")
    TaskService taskService;

    DefaultUserConfiguration defaultUserConfiguration;

    @NotNull
    @GetMapping()
    public TodolistResponse<List<Task>> getAll() {
        List<Task> taskList = taskService.getAll(defaultUserConfiguration.getDefaultUser());

        return TodolistResponse.ok(taskList);
    }

    @NotNull
    @GetMapping("/active")
    public TodolistResponse<List<Task>> getActive() {
        List<Task> taskList = taskService.getByActive(true, defaultUserConfiguration.getDefaultUser());

        return TodolistResponse.ok(taskList);
    }

    @NotNull
    @GetMapping("/finished")
    public TodolistResponse<List<Task>> getFinished() {
        List<Task> taskList = taskService.getByActive(false, defaultUserConfiguration.getDefaultUser());

        return TodolistResponse.ok(taskList);
    }

    @NotNull
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public TodolistResponse<String> addTask(@Valid @RequestBody TaskAddRequest request) {
        taskService.add(request, defaultUserConfiguration.getDefaultUser());

        return TodolistResponse.ok();
    }

    @NotNull
    @PutMapping("/update/{id}")
    public TodolistResponse<String> updateTask(
            @PathVariable int id, @Valid @RequestBody TaskChangeRequest request) {
        taskService.update(id, request, defaultUserConfiguration.getDefaultUser());

        return TodolistResponse.ok();
    }

    @NotNull
    @DeleteMapping("/delete/{id}")
    public TodolistResponse<String> deleteTask(@PathVariable int id) {
        taskService.delete(id, defaultUserConfiguration.getDefaultUser());

        return TodolistResponse.ok();
    }

}
