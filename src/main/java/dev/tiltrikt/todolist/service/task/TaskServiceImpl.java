package dev.tiltrikt.todolist.service.task;

import com.github.dozermapper.core.Mapper;
import dev.tiltrikt.todolist.exception.TaskNotFoundException;
import dev.tiltrikt.todolist.model.Task;
import dev.tiltrikt.todolist.request.TaskAddRequest;
import dev.tiltrikt.todolist.repository.TaskRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskServiceImpl implements TaskService {

    TaskRepository taskRepository;

    Mapper mapper;

    @Override
    @NotNull
    public List<Task> getAll() {

        return taskRepository.findAll();
    }

    @Override
    @NotNull
    public List<Task> getByActive(boolean active) {

        return taskRepository.findByActive(active);
    }

    @Override
    public void update(int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setActive(!task.isActive());
            taskRepository.save(task);
            log.debug("task with id: {}, active state was changed from {} to {}",
                    task.getId(), !task.isActive(), task.isActive());
        } else {
            throw new TaskNotFoundException(String.format("task with id %d wasn't found", id));
        }
    }

    @Override
    public void delete(int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            taskRepository.delete(optionalTask.get());
        } else {
            throw new TaskNotFoundException(String.format("task with id %d wasn't found", id));
        }
    }

    @Override
    public void add(@NotNull TaskAddRequest request) {
        Task task = mapper.map(request, Task.class);
        Optional<Task> optionalTask = taskRepository.findById(task.getId());
        if (optionalTask.isEmpty()) {
            taskRepository.save(task);
        } else {
            log.debug("task with id {} already exists: {}", task.getId(), optionalTask.get());
        }
    }
}
