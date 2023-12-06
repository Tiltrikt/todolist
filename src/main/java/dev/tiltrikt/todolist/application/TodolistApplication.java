package dev.tiltrikt.todolist.application;

import dev.tiltrikt.todolist.service.CommandBootstrap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "dev.tiltrikt.todolist")
public class TodolistApplication {

    private static final CommandBootstrap commandBootstrap = new CommandBootstrap();

    public static void main(String[] args) {
        SpringApplication.run(TodolistApplication.class, args);
        commandBootstrap.bootstrap();
    }
}
