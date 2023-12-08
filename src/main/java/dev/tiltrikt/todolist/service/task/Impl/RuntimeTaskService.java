package dev.tiltrikt.todolist.service.task.Impl;

import dev.tiltrikt.todolist.Task;
import dev.tiltrikt.todolist.service.task.TaskService;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

@Getter
@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RuntimeTaskService implements TaskService {

  private final List<Task> taskList = new ArrayList<>();

  public @Nullable Task getById(int id) {
    return taskList.stream()
        .filter(task -> task.getId() == id)
        .findFirst().orElse(null);
  }

  public boolean exists(int id) {
    return getById(id) != null;
  }

  public void addTask(@NotNull Task task) {
    taskList.add(task);
  }

  @SuppressWarnings("DataFlowIssue")
  public void finishTask(int id) {
    getById(id).finish();
  }

}
