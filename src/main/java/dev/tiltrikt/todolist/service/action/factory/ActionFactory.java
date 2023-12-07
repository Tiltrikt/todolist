package dev.tiltrikt.todolist.service.action.factory;

import dev.tiltrikt.todolist.service.action.Action;
import org.jetbrains.annotations.NotNull;

public interface ActionFactory {

  @NotNull Action getAction(@NotNull String commandName);
}
