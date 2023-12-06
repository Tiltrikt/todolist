package dev.tiltrikt.todolist.service;

import dev.tiltrikt.todolist.service.console.ConsoleService;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommandBootstrap implements CommandLineRunner {

    Scanner scanner = new Scanner(System.in);
    @Autowired
    @NonFinal
    ConsoleService consoleService;

    @Override
    public void run(String... args) throws Exception {
        bootstrap();
    }

    public void bootstrap() {
        consoleService.printUsageInfo();
        while (true) {
            consoleService.printCommandLinePrompt();
            String line = scanner.nextLine();
            switch (line.toLowerCase()) {
                case "exit" -> System.exit(0);
                case "help" -> consoleService.printUsageInfo();
                case "add" -> consoleService.askToAddTask();
                case "active" -> consoleService.printActive();
                case "finish" -> consoleService.askToFinishTask();
                case "finished" -> consoleService.printFinished();
                default -> {
                    System.out.println("Command not found!");
                    consoleService.printUsageInfo();
                }
            }
        }
    }

}
