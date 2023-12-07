package dev.tiltrikt.todolist.service.console.action;

import java.util.List;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface Action {

  void execute(@NotNull List<String> args);
}
