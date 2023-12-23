package dev.tiltrikt.todolist.service.task;

import dev.tiltrikt.todolist.exception.TaskException;
import dev.tiltrikt.todolist.model.Task;
import dev.tiltrikt.todolist.dto.task.TaskAddRequest;
import dev.tiltrikt.todolist.dto.task.TaskChangeRequest;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface TaskService {

    @NotNull List<Task> getAll();

    @NotNull List<Task> getByActive(boolean active);

    void update(int id, TaskChangeRequest request) throws TaskException;

    void delete(int id) throws TaskException;

    void add(@NotNull TaskAddRequest request);
}
