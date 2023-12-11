package dev.tiltrikt.todolist.controller;

import dev.tiltrikt.todolist.dto.TaskDTO;
import dev.tiltrikt.todolist.service.TaskService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v${app.version}/tasks")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskController {

    TaskService taskService;

    @GetMapping()
    public ResponseEntity<List<TaskDTO>> getAll() {
        List<TaskDTO> taskList = taskService.getAll();

        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<List<TaskDTO>> getActive() {
        List<TaskDTO> taskList = taskService.getByActive(true);

        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

    @GetMapping("/finished")
    public ResponseEntity<List<TaskDTO>> getFinished() {
        List<TaskDTO> taskList = taskService.getByActive(true);

        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTask(@Valid @RequestBody TaskDTO task) {
        boolean isAdded = taskService.add(task);

        return isAdded ?
                new ResponseEntity<>(HttpStatus.CREATED) :
                new ResponseEntity<>(HttpStatus.FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTask(@PathVariable int id) {
        boolean isUpdated = taskService.update(id);

        return isUpdated ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable int id) {
        boolean isDeleted = taskService.delete(id);

        return isDeleted ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
