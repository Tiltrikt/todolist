package dev.tiltrikt.todolist.service.task.Impl;

import dev.tiltrikt.todolist.Task;
import dev.tiltrikt.todolist.service.task.TaskService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RuntimeTaskService implements TaskService {

    List<Task> taskList = new ArrayList<>();

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

    public void finishTask(int id) {
        getById(id).finish();
    }

}
