//package dev.tiltrikt.todolist.service;
//
//import dev.tiltrikt.todolist.model.Task;
//import dev.tiltrikt.todolist.repository.TaskRepository;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//public class TaskService {
//
//    TaskRepository taskRepository;
//
//    public List<Task> getActive(){
//        return taskRepository.findByActive(true);
//    }
//
//    public List<Task> getFinished(){
//        return taskRepository.findByActive(false);
//    }
//
//    public void save(Task task) {
//        taskRepository.save(task);
//    }
//
//    public void update(Task task) {
//        taskRepository.save(task);
//    }
//}
