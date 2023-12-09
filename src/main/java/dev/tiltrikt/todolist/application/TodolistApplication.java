package dev.tiltrikt.todolist.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "dev.tiltrikt.todolist")
@EnableJpaRepositories(basePackages="dev.tiltrikt.todolist")
@EntityScan(basePackages="dev.tiltrikt.todolist")
public class TodolistApplication {

  /* TODO: 08.12.2023
    Postman
    1) REST. Стандарт нейминга эндпоинтов контроллера. GET POST PUT DELETE UPDATE CREATE
    2) Spring Data (JDBC Template, JPA)
    4) com.github.dozermapper (Автомаппер)
    3) Контроллеры: TaskController:
      /v1/tasks - вернуть все задачи
      /v1/tasks/active - вернуть активные задачи
      /v1/tasks/finished - вернуть законченые задачи
      /v1/tasks/ - добавить задачу
      /v1/tasks/ - отредактировать задачу
      /v1/tasks/ - удалить задачу
    4) Профили приложения dev, debug, prod, postgres, mysql
   */

  public static void main(String[] args) {
    SpringApplication.run(TodolistApplication.class, args);

  }
}
