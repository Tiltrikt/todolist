package dev.tiltrikt.todolist.application;

import com.sun.jdi.Bootstrap;
import dev.tiltrikt.todolist.service.CommandBootstrap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "dev.tiltrikt.todolist")
public class TodolistApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodolistApplication.class, args);
        new CommandBootstrap().bootstrap();
    }
}
