package dev.tiltrikt.todolist.service.action.factory;

import dev.tiltrikt.todolist.service.action.ActionService;
import org.jetbrains.annotations.NotNull;

public interface ActionFactory {

  @NotNull ActionService getAction(@NotNull String commandName);
}
