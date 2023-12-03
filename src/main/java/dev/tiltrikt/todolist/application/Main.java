package dev.tiltrikt.todolist.application;

import dev.tiltrikt.todolist.service.CommandBootstrap;
import org.springframework.boot.SpringApplication;

public class Main {

    public static void main(String[] args) {
        SpringApplication.run(CommandBootstrap.class, args);
    }
}
