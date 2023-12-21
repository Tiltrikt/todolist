package dev.tiltrikt.todolist.service.task;

import com.github.dozermapper.core.Mapper;
import dev.tiltrikt.todolist.exception.TaskException;
import dev.tiltrikt.todolist.model.Task;
import dev.tiltrikt.todolist.request.TaskAddRequest;
import dev.tiltrikt.todolist.repository.TaskRepository;
import dev.tiltrikt.todolist.request.TaskChangeRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void update(int id, TaskChangeRequest request) throws TaskException {
        Task task = getTask(id);

        if (request.getText() != null) task.setText(request.getText());
        if (request.getActive() != null) task.setActive(request.getActive());

        taskRepository.save(task);
    }

    @Override
    public void delete(int id) throws TaskException {

        taskRepository.delete(getTask(id));
    }

    @Override
    public void add(@NotNull TaskAddRequest request) {

        taskRepository.save(mapper.map(request, Task.class));
    }

    private Task getTask(int id) throws TaskException{
        return taskRepository
                .findById(id)
                .orElseThrow(() -> new TaskException(String.format("task with id %d wasn't found", id)));
    }
}
