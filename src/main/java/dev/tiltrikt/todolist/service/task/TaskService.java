package dev.tiltrikt.todolist.service.task;

import dev.tiltrikt.todolist.exception.TaskException;
import dev.tiltrikt.todolist.model.Task;
import dev.tiltrikt.todolist.dto.task.TaskAddRequest;
import dev.tiltrikt.todolist.dto.task.TaskChangeRequest;
import dev.tiltrikt.todolist.model.User;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface TaskService {

    @NotNull List<Task> getAll(@NotNull User user);

    @NotNull List<Task> getByActive(boolean active, @NotNull User user);

    void update(int id, @NotNull TaskChangeRequest request, @NotNull User user) throws TaskException;

    void delete(int id, @NotNull User user) throws TaskException;

    void add(@NotNull TaskAddRequest request, @NotNull User user);
}
