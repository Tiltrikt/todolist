package dev.tiltrikt.todolist.service.task;

import com.github.dozermapper.core.Mapper;
import dev.tiltrikt.todolist.dto.TaskDTO;
import dev.tiltrikt.todolist.model.Task;
import dev.tiltrikt.todolist.repository.TaskRepository;
import dev.tiltrikt.todolist.service.task.TaskService;
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

    Mapper mapper;

    @Override
    public List<TaskDTO> getAll() {
        return taskRepository.findAll().stream()
                .map((task) -> mapper.map(task, TaskDTO.class))
                .toList();
    }

    @Override
    public List<TaskDTO> getByActive(boolean active) {
        return taskRepository.findByActive(active).stream()
                .map((task) -> mapper.map(task, TaskDTO.class))
                .toList();
    }

    @Override
    public boolean update(int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setActive(!task.isActive());
            taskRepository.save(task);
            return true;
        } else
            return false;
    }

    @Override
    public boolean delete(int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            taskRepository.delete(optionalTask.get());
            return true;
        } else
            return false;
    }

    @Override
    public boolean add(TaskDTO taskDTO) {
        Task task = mapper.map(taskDTO, Task.class);
        Optional<Task> optionalTask = taskRepository.findById(task.getId());
        if (optionalTask.isEmpty()) {
            taskRepository.save(task);
            return true;
        } else
            return false;
    }
}
