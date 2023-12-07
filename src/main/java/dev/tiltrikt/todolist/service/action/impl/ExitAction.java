package dev.tiltrikt.todolist.service.action.impl;

import dev.tiltrikt.todolist.service.action.ActionService;

import java.util.List;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component("exitAction")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExitAction implements ActionService {

    ConfigurableApplicationContext applicationContext;

    @Override
    public void execute(@NotNull List<String> args) {
        applicationContext.close();
        System.exit(0);
    }
}
