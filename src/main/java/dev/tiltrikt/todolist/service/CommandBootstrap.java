package dev.tiltrikt.todolist.service;

import dev.tiltrikt.todolist.service.console.ConsoleService;
import dev.tiltrikt.todolist.service.console.action.Action;
import dev.tiltrikt.todolist.service.console.action.factory.ActionFactory;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommandBootstrap implements CommandLineRunner {

  @NotNull Scanner scanner = new Scanner(System.in);

  @NotNull
  ConsoleService consoleService;

  @NotNull
  ActionFactory actionFactory;

  @Override
  @SuppressWarnings("InfiniteLoopStatement")
  public void run(String @NotNull ... args) {
    consoleService.printUsageInfo().run();
    consoleService.printCommandLinePrompt();
    while (true) {
      String line = scanner.nextLine();
      List<String> arguments = Arrays.stream(line.split(" "))
          .toList();
      handleAction(arguments);
    }
  }

  private void handleAction(@NotNull List<String> arguments) {
    try {
      Action action = actionFactory.getAction(arguments.get(0));
      action.execute(arguments.stream().skip(1).toList());
    } catch (Exception exception) {
      System.out.println("Invalid command. Type help...");
    }
  }
}
