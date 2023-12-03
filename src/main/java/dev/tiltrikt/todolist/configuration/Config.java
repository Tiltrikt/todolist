package dev.tiltrikt.todolist.configuration;

import dev.tiltrikt.todolist.service.CommandBootstrap;
import dev.tiltrikt.todolist.service.console.ConsoleService;
import dev.tiltrikt.todolist.service.console.Impl.ConsoleServiceImpl;
import dev.tiltrikt.todolist.service.task.Impl.RuntimeTaskService;
import dev.tiltrikt.todolist.service.task.TaskService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
@ComponentScan("dev.tiltrikt.todolist")
public class Config {

//    @Bean
//    public CommandBootstrap commandBootstrapBean() {
//        return new CommandBootstrap(consoleServiceBean());
//    }
//
//    @Bean
//    public ConsoleService consoleServiceBean() {
//        return new ConsoleServiceImpl(taskServiceBean());
//    }
//
//    @Bean
//    public TaskService taskServiceBean() {
//        return new RuntimeTaskService();
//    }

}
