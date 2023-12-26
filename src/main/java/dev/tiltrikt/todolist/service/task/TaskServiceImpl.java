package dev.tiltrikt.todolist.service.task;

import com.github.dozermapper.core.Mapper;
import dev.tiltrikt.todolist.exception.TaskException;
import dev.tiltrikt.todolist.model.Task;
import dev.tiltrikt.todolist.dto.task.TaskAddRequest;
import dev.tiltrikt.todolist.model.User;
import dev.tiltrikt.todolist.dto.task.TaskChangeRequest;
import dev.tiltrikt.todolist.repository.UserRepository;
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

    UserRepository userRepository;
    Mapper mapper;

    @Override
    @NotNull
    public List<Task> getAll(@NotNull User user) {

        return user.getTaskList();
    }

    @Override
    @NotNull
    public List<Task> getByActive(boolean active, @NotNull User user) {

        return user.getTaskList().stream()
                .filter(task -> task.isActive() == active)
                .toList();
    }

    @Override
    public void update(int id, @NotNull TaskChangeRequest request, @NotNull User user) throws TaskException {

        Task task = user.getTaskList().stream()
                .filter((task1 -> task1.getId() == id))
                .findFirst()
                .orElseThrow(() -> new TaskException(String.format("task with id %d wasn't found", id)));

        if (request.getText() != null) task.setText(request.getText());
        if (request.getActive() != null) task.setActive(request.getActive());

        userRepository.save(user);
    }

    @Override
    public void delete(int id, @NotNull User user) throws TaskException {

        Task task = user.getTaskList().stream()
                .filter((task1 -> task1.getId() == id))
                .findFirst()
                .orElseThrow(() -> new TaskException(String.format("task with id %d wasn't found", id)));

        user.getTaskList().remove(task);

        userRepository.save(user);
    }

    @Override
    public void add(@NotNull TaskAddRequest request, @NotNull User user) {

        user.getTaskList().add(mapper.map(request, Task.class));
        userRepository.save(user);
    }
}
