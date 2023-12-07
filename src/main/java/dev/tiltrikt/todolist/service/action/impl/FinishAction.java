package dev.tiltrikt.todolist.service.action.impl;

import dev.tiltrikt.todolist.service.action.Action;
import dev.tiltrikt.todolist.service.task.TaskService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("finishAction")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FinishAction implements Action {

    TaskService taskService;

    @Override
    public void execute(@NotNull List<String> args) {
        if (!taskService.exists(Integer.parseInt(args.get(0)))) {
            System.out.println("Task not found!");
            return;
        }
        taskService.finishTask(Integer.parseInt(args.get(0)));
        System.out.println("Task successfully finished!");
    }
}
