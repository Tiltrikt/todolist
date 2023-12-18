package dev.tiltrikt.todolist.repository;

import dev.tiltrikt.todolist.model.Task;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

  @NotNull List<Task> findByActive(boolean state);
}
