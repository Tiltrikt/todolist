package dev.tiltrikt.todolist.service.task;

import com.github.dozermapper.core.Mapper;
import dev.tiltrikt.todolist.aspect.UserDependent;
import dev.tiltrikt.todolist.exception.TaskException;
import dev.tiltrikt.todolist.model.Task;
import dev.tiltrikt.todolist.dto.task.TaskAddRequest;
import dev.tiltrikt.todolist.model.User;
import dev.tiltrikt.todolist.dto.task.TaskChangeRequest;
import dev.tiltrikt.todolist.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskServiceMultipleUser implements TaskService, UserDependent {

    UserRepository userRepository;

    Mapper mapper;

    @Setter
    @NonFinal
    User user;

    @Override
    @NotNull
    public List<Task> getAll() {

        return user.getTaskList();
    }

    @Override
    @NotNull
    public List<Task> getByActive(boolean active) {

        return user.getTaskList().stream()
                .filter(task -> task.isActive() == active)
                .toList();
    }

    @Override
    public void update(int id, TaskChangeRequest request) throws TaskException {

        Task task = user.getTaskList().stream()
                .filter((task1 -> task1.getId() == id))
                .findFirst()
                .orElseThrow(() -> new TaskException(String.format("task with id %d wasn't found", id)));

        if (request.getText() != null) task.setText(request.getText());
        if (request.getActive() != null) task.setActive(request.getActive());

        userRepository.save(user);
    }

    @Override
    public void delete(int id) throws TaskException {

        Task task = user.getTaskList().stream()
                .filter((task1 -> task1.getId() == id))
                .findFirst()
                .orElseThrow(() -> new TaskException(String.format("task with id %d wasn't found", id)));

        user.getTaskList().remove(task);

        userRepository.save(user);
    }

    @Override
    public void add(@NotNull TaskAddRequest request) {

        user.getTaskList().add(mapper.map(request, Task.class));
        userRepository.save(user);
    }
}
