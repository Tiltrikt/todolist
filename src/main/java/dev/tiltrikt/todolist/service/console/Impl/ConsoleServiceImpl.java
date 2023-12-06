package dev.tiltrikt.todolist.service.console.Impl;

import dev.tiltrikt.todolist.Task;
import dev.tiltrikt.todolist.service.console.ConsoleService;
import dev.tiltrikt.todolist.service.task.TaskService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsoleServiceImpl implements ConsoleService {

    Scanner scanner = new Scanner(System.in);
    @Autowired
    @NonFinal
    TaskService taskService;

    @Override
    public void printActive() {
        System.out.println("Your tasks:");
        taskService.getTaskList().stream()
                .filter(Task::active)
                .forEach(task ->
                        System.out.printf("%d: %s%n",
                                task.getId(),
                                task.getText())
                );
    }

    @Override
    public void printFinished() {
        System.out.println("Your finished tasks:");
        taskService.getTaskList().
                stream()
                .filter(Task::finished)
                .forEach(task ->
                        System.out.printf("%d: %s%n",
                                task.getId(),
                                task.getText()
                        )
                );
    }

    @Override
    public void askToAddTask() {
        System.out.println("Enter task id:");
        printCommandLinePrompt();
        int id = scanner.nextInt();
        scanner.nextLine();
        if (taskService.exists(id)) {
            System.out.println("Task is already active!");
            return;
        }
        System.out.println("Enter task content:");
        printCommandLinePrompt();
        String content = scanner.nextLine();
        taskService.addTask(
                Task.builder()
                        .id(id)
                        .text(content)
                        .build()
        );
        System.out.println("Task successfully activated!");
    }

    @Override
    public void askToFinishTask() {
        System.out.println("Enter task id:");
        printCommandLinePrompt();
        int id = scanner.nextInt();
        scanner.nextLine();
        if (!taskService.exists(id)) {
            System.out.println("Task not found!");
            return;
        }
        taskService.finishTask(id);
        System.out.println("Task successfully finished!");
    }

    @Override
    public void printUsageInfo() {
        System.out.println("Usage info:");
        System.out.println("help -> shows this information");
        System.out.println("add -> add task to todolist");
        System.out.println("finish -> finish task in todolist");
        System.out.println("active -> display all active tasks");
        System.out.println("finished -> display all finished tasks");
    }

    @Override
    public void printCommandLinePrompt() {
        System.out.print("tl > ");
    }
}
