package dev.tiltrikt.todolist.controller;

import dev.tiltrikt.todolist.model.Task;
import dev.tiltrikt.todolist.service.TaskService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v${app.version}/tasks")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskController {

    TaskService taskService;

    @GetMapping()
    public List<Task> getAll() {
        return taskService.getAll();
    }

    @GetMapping("/active")
    public List<Task> getActive() {
        return taskService.getByActive(true);
    }

    @GetMapping("/finished")
    public List<Task> getFinished() {
        return taskService.getByActive(false);
    }

    @PostMapping("/add")
    public void addTask(@RequestBody Task task) {
        taskService.add(task);
    }

    @PutMapping("/update")
    public void updateTask(@RequestBody Integer id) {
        taskService.update(id);
    }

    @DeleteMapping("/delete")
    public void deleteTask(@RequestBody Integer id) {
        taskService.delete(id);
    }
}
