package dev.tiltrikt.todolist.service;

import dev.tiltrikt.todolist.model.Task;

import java.util.List;

public interface TaskService {

    List<Task> getAll();
    List<Task> getByActive(boolean active);
    void update(int id);
    void delete(int id);
    void add(Task task);
}
