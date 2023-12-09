package dev.tiltrikt.todolist.repository;

import dev.tiltrikt.todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByActive(boolean state);
    void deleteById(int id);
//    boolean updateById(int id);
}
