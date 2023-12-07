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
import java.util.stream.Collectors;

@Component("addAction")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddAction implements Action {

    TaskService taskService;

    @Override
    public void execute(@NotNull List<String> args) {
        if (taskService.exists(Integer.parseInt(args.get(0)))) {
            System.out.println("Task is already active!");
            return;
        }
        if (args.size() < 2) throw new IllegalArgumentException();
        String taskText = args.stream()
                .skip(1)
                .collect(Collectors.joining(" "));
        taskService.addTask(
                Task.builder()
                        .id(Integer.parseInt(args.get(0)))
                        .text(taskText)
                        .build()
        );
        System.out.println("Task successfully activated!");
    }
}
