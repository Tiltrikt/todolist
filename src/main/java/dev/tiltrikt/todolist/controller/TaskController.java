package dev.tiltrikt.todolist.controller;

import dev.tiltrikt.todolist.model.Task;
import dev.tiltrikt.todolist.repository.TaskRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/tasks")
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @GetMapping("/tasks/active")
    public List<Task> getActive() {
        return taskRepository.findByActive(true);
    }

    @GetMapping("/tasks/finished")
    public List<Task> getFinished() {
        return taskRepository.findByActive(false);
    }

    @PostMapping("/tasks/add")
    public void addTask() {
        taskRepository.save(new Task());
    }

    @PutMapping("/tasks/update")
    public void updateTask(Task task) {
        taskRepository.save(task);
    }

    @DeleteMapping("/tasks/delete")
    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }
}
