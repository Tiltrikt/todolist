package dev.tiltrikt.todolist.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "dev.tiltrikt.todolist.repository")
@EntityScan(basePackages = "dev.tiltrikt.todolist.model")
public class DataConfiguration {
}
