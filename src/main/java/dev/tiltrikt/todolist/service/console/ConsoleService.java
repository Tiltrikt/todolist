package dev.tiltrikt.todolist.service.console;

public interface ConsoleService {
    void printActive();

    void printFinished();

    void askToAddTask();

    void askToFinishTask();

    void printUsageInfo();

    void printCommandLinePrompt();

}
