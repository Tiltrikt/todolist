package dev.tiltrikt.todolist.service.action.impl;

import dev.tiltrikt.todolist.service.action.ActionService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("printCommandLinePromptAction")
public class PrintCommandLinePromptAction implements ActionService {

    @Override
    public void execute(@NotNull List<String> args) {
        System.out.print("tl > ");
    }
}
