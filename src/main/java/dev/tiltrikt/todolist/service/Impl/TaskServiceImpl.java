package dev.tiltrikt.todolist.service.Impl;

import dev.tiltrikt.todolist.model.Task;
import dev.tiltrikt.todolist.repository.TaskRepository;
import dev.tiltrikt.todolist.service.TaskService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskServiceImpl implements TaskService {

    TaskRepository taskRepository;

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getByActive(boolean active) {
        return taskRepository.findByActive(active);
    }

    @Override
    public void update(int id) {
        Optional<Task> task = taskRepository.findById(id);
        task.ifPresent(t -> {
            t.setActive(!t.isActive());
            taskRepository.save(t);
        });
    }

    @Override
    public void delete(int id) {
        Optional<Task> task = taskRepository.findById(id);
        task.ifPresent(taskRepository::delete);
    }

    @Override
    public void add(Task task) {
        Optional<Task> optionalTask = taskRepository.findById(task.getId());
        if(optionalTask.isEmpty())
            taskRepository.save(task);
    }
}
