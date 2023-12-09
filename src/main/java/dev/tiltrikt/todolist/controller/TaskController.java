package dev.tiltrikt.todolist.controller;

import dev.tiltrikt.todolist.model.Task;
import dev.tiltrikt.todolist.repository.TaskRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public void addTask(@RequestBody Map<String, String> info) {
        taskRepository.save(Task.builder()
                .id(Integer.parseInt(info.get("id")))
                .text(info.get("text"))
                .build());
    }

    @PutMapping("/tasks/update")
    public void updateTask(@RequestBody Map<String, String> info) {
        Optional<Task> task = taskRepository.findById(Integer.parseInt(info.get("id")));
        task.ifPresent(task1 -> {
            task1.setActive(Boolean.parseBoolean(info.get("active")));
            taskRepository.save(task1);
        });

    }

    @DeleteMapping("/tasks/delete")
    public void deleteTask(@RequestBody Integer id) {
        taskRepository.deleteById(id);
    }
}
