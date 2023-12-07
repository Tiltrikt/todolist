package dev.tiltrikt.todolist.service.console;

public interface ConsoleService {
  Runnable printActive();

  Runnable printFinished();

  Runnable askToAddTask();

  Runnable askToFinishTask();

  Runnable printUsageInfo();

  void printCommandLinePrompt();

}
