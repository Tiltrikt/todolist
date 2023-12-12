package dev.tiltrikt.todolist.service.task;

import dev.tiltrikt.todolist.dto.TaskDTO;

import java.util.List;

public interface TaskService {

    List<TaskDTO> getAll();

    List<TaskDTO> getByActive(boolean active);

    boolean update(int id);

    boolean delete(int id);

    boolean add(TaskDTO taskDTO);
}
