package dev.tiltrikt.todolist.service.task;

import dev.tiltrikt.todolist.Task;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface TaskService {
  @NotNull List<Task> getTaskList();

  @Nullable Task getById(int id);

  boolean exists(int id);

  void addTask(@NotNull Task task);

  void finishTask(int id);

}
