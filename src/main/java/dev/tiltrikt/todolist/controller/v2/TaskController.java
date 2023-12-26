package dev.tiltrikt.todolist.controller.v2;

import dev.tiltrikt.todolist.model.Task;
import dev.tiltrikt.todolist.dto.task.TaskAddRequest;
import dev.tiltrikt.todolist.dto.task.TaskChangeRequest;
import dev.tiltrikt.todolist.model.User;
import dev.tiltrikt.todolist.response.TodolistResponse;
import dev.tiltrikt.todolist.service.task.TaskService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("taskControllerV2")
@RequiredArgsConstructor
@RequestMapping("/v2/tasks")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskController {

    TaskService taskService;

    @NotNull
    @GetMapping()
    public TodolistResponse<List<Task>> getAll(@AuthenticationPrincipal User user) {
        List<Task> taskList = taskService.getAll(user);

        return TodolistResponse.ok(taskList);
    }

    @NotNull
    @GetMapping("/active")
    public TodolistResponse<List<Task>> getActive(@AuthenticationPrincipal User user) {
        List<Task> taskList = taskService.getByActive(true, user);

        return TodolistResponse.ok(taskList);
    }

    @NotNull
    @GetMapping("/finished")
    public TodolistResponse<List<Task>> getFinished(@AuthenticationPrincipal User user) {
        List<Task> taskList = taskService.getByActive(false, user);

        return TodolistResponse.ok(taskList);
    }

    @NotNull
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public TodolistResponse<String> addTask(@Valid @RequestBody TaskAddRequest request,
                                            @AuthenticationPrincipal User user) {
        taskService.add(request, user);

        return TodolistResponse.ok();
    }

    @NotNull
    @PutMapping("/update/{id}")
    public TodolistResponse<String> updateTask(
            @PathVariable int id,
            @Valid @RequestBody TaskChangeRequest request,
            @AuthenticationPrincipal User user) {

        taskService.update(id, request, user);

        return TodolistResponse.ok();
    }

    @NotNull
    @DeleteMapping("/delete/{id}")
    public TodolistResponse<String> deleteTask(@PathVariable int id,
                                               @AuthenticationPrincipal User user) {

        taskService.delete(id, user);

        return TodolistResponse.ok();
    }

}
