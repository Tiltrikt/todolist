package dev.tiltrikt.todolist.service.task;

import dev.tiltrikt.todolist.model.Task;
import dev.tiltrikt.todolist.request.TaskAddRequest;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface TaskService {

    @NotNull List<Task> getAll();

    @NotNull List<Task> getByActive(boolean active);

    void update(int id);

    void delete(int id);

    void add(@NotNull TaskAddRequest request);
}
