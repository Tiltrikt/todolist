package dev.tiltrikt.todolist.service.action.impl;

import dev.tiltrikt.todolist.service.action.ActionService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("helpAction")
public class HelpAction implements ActionService {

    @Override
    public void execute(@NotNull List<String> args) {
        System.out.println("Usage info:");
        System.out.println("help -> shows this information");
        System.out.println("add -> add task to todolist");
        System.out.println("finish -> finish task in todolist");
        System.out.println("active -> display all active tasks");
        System.out.println("finished -> display all finished tasks");
    }
}
