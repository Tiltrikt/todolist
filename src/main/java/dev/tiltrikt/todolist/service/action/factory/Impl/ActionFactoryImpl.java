package dev.tiltrikt.todolist.service.action.factory.Impl;

import dev.tiltrikt.todolist.service.action.factory.ActionFactory;
import dev.tiltrikt.todolist.service.action.ActionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Setter
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ActionFactoryImpl implements ActionFactory {

  ApplicationContext applicationContext;

  @Override
  public @NotNull ActionService getAction(@NotNull String commandName) {
    return applicationContext.getBean(commandName + "Action", ActionService.class);
  }
}
