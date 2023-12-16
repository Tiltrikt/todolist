package dev.tiltrikt.todolist.controller.v1;

import dev.tiltrikt.todolist.model.Task;
import dev.tiltrikt.todolist.request.TaskAddRequest;
import dev.tiltrikt.todolist.response.UniverseResponse;
import dev.tiltrikt.todolist.service.task.TaskService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/tasks")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskController {

    TaskService taskService;

    @NotNull
    @GetMapping()
    public ResponseEntity<UniverseResponse<List<Task>>> getAll() {
        List<Task> taskList = taskService.getAll();

        return new ResponseEntity<>(UniverseResponse.<List<Task>>builder()
                .payload(taskList)
                .build(),
                HttpStatus.OK
        );
    }

    @NotNull
    @GetMapping("/active")
    public ResponseEntity<UniverseResponse<List<Task>>> getActive() {
        List<Task> taskList = taskService.getByActive(true);

        return new ResponseEntity<>(UniverseResponse.<List<Task>>builder()
                .payload(taskList)
                .build(),
                HttpStatus.OK
        );
    }

    @NotNull
    @GetMapping("/finished")
    public ResponseEntity<UniverseResponse<List<Task>>> getFinished() {
        List<Task> taskList = taskService.getByActive(false);

        return new ResponseEntity<>(UniverseResponse.<List<Task>>builder()
                .payload(taskList)
                .build(),
                HttpStatus.OK
        );
    }

    @NotNull
    @PostMapping("/add")
    public ResponseEntity<UniverseResponse<String>> addTask(@Valid @RequestBody TaskAddRequest request) {
        taskService.add(request);

        return new ResponseEntity<>(new UniverseResponse<>(), HttpStatus.CREATED);
    }

    @NotNull
    @PutMapping("/update/{id}")
    public ResponseEntity<UniverseResponse<String>> updateTask(@Valid @PathVariable int id) {
        taskService.update(id);

        return new ResponseEntity<>(new UniverseResponse<>(), HttpStatus.OK);
    }

    @NotNull
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UniverseResponse<String>> deleteTask(@PathVariable int id) {
        taskService.delete(id);

        return new ResponseEntity<>(new UniverseResponse<>(), HttpStatus.OK);
    }

}
