package dev.tiltrikt.todolist.service;

import dev.tiltrikt.todolist.service.console.ConsoleService;
import dev.tiltrikt.todolist.service.console.Impl.ConsoleServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommandBootstrap implements CommandLineRunner {

    Scanner scanner = new Scanner(System.in);
    ConsoleService consoleService;
    ApplicationContext context;

    @Override
    public void run(String... args) throws Exception {

        consoleService.printUsageInfo().run();
        while(true) {
            consoleService.printCommandLinePrompt();
            String line = scanner.nextLine();
            try {
                context.getBean(line, Runnable.class).run();
            } catch (Exception exception) {
                if (line.equals("exit")) System.exit(1);
                consoleService.printUsageInfo().run();
            }
        }
    }

}
