package dev.tiltrikt.todolist.service.console.action.factory;

import dev.tiltrikt.todolist.service.console.action.Action;
import org.jetbrains.annotations.NotNull;

public interface ActionFactory {

  @NotNull Action getAction(@NotNull String commandName);
}
