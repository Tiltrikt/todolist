package dev.tiltrikt.todolist.service.action.impl;

import dev.tiltrikt.todolist.Task;
import dev.tiltrikt.todolist.service.action.Action;
import dev.tiltrikt.todolist.service.task.TaskService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("finishedAction")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FinishedAction implements Action {

    TaskService taskService;

    @Override
    public void execute(@NotNull List<String> args) {
        System.out.println("Your finished tasks:");
        taskService.getTaskList().
                stream()
                .filter(Task::finished)
                .forEach(task ->
                        System.out.printf("%d: %s%n",
                                task.getId(),
                                task.getText()
                        )
                );
    }
}
