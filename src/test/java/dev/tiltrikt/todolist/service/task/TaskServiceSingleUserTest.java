//package dev.tiltrikt.todolist.service.task;
//
//import com.github.dozermapper.core.Mapper;
//import dev.tiltrikt.todolist.configuration.DozerConfiguration;
//import dev.tiltrikt.todolist.exception.TaskException;
//import dev.tiltrikt.todolist.model.Task;
//import dev.tiltrikt.todolist.repository.TaskRepository;
//import dev.tiltrikt.todolist.dto.task.TaskAddRequest;
//import dev.tiltrikt.todolist.dto.task.TaskChangeRequest;
//import jakarta.persistence.EntityManager;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.jdbc.Sql;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_CLASS;
//
//@DataJpaTest
//@ContextConfiguration(classes = {TaskRepository.class, TaskServiceSingleUser.class, DozerConfiguration.class})
//@EnableJpaRepositories(basePackages = {"dev.tiltrikt.todolist.repository"})
//@EntityScan("dev.tiltrikt.todolist.model")
//@Sql(value = {"/schema_td.sql", "/data_td.sql"}, executionPhase = BEFORE_TEST_CLASS)
//@ActiveProfiles("test")
//class TaskServiceSingleUserTest {
//
//    @Autowired
//    TaskRepository taskRepository;
//
//    @Autowired
//    TaskService taskService;
//
//    @Autowired
//    EntityManager entityManager;
//
//    @Autowired
//    Mapper mapper;
//
//    @Test
//    void getAllMustReturnList() {
//        List<Task> taskList = new ArrayList<>();
//        taskList.add(Task.builder()
//                .id(1)
//                .text("work")
//                .active(true).build());
//        taskList.add(Task.builder()
//                .id(2)
//                .text("sleep")
//                .active(false).build());
//
//        assertEquals(taskList, taskService.getAll());
//    }
//
//    @Test
//    void getByActiveWithTrueMustReturnListOfActiveOnly() {
//        List<Task> taskList = new ArrayList<>();
//        taskList.add(Task.builder()
//                .id(1)
//                .text("work")
//                .active(true).build());
//
//        assertEquals(taskList, taskService.getByActive(true));
//    }
//
//    @Test
//    void getByActiveWithFalseMustReturnListOfFinishedOnly() {
//        List<Task> taskList = new ArrayList<>();
//        taskList.add(Task.builder()
//                .id(2)
//                .text("sleep")
//                .active(false).build());
//
//        assertEquals(taskList, taskService.getByActive(false));
//    }
//
//    @Test
//    void updateTaskWithIdThatExistMustChangeTaskInDatabase() {
//        Task task = entityManager.find(Task.class, 1);
//        assertTrue(task.isActive());
//        assertEquals(task.getText(), "work");
//
//        taskService.update(1, new TaskChangeRequest("jump", false));
//
//        assertFalse(task.isActive());
//        assertEquals(task.getText(), "jump");
//    }
//
//    @Test
//    void updateTaskWithIdThatDoesntExistMustThrowException() {
//        assertThrows(
//                TaskException.class,
//                () -> taskService.update(-1, new TaskChangeRequest("jump", false))
//        );
//    }
//
//    @Test
//    void deleteTaskWithIdThatExistMustRemoveTaskInDatabase() {
//        Task task = entityManager.find(Task.class, 1);
//        assertTrue(entityManager.contains(task));
//
//        taskService.delete(1);
//
//        assertFalse(entityManager.contains(task));
//    }
//
//    @Test
//    void deleteTaskWithIdThatDoesntExistMustThrowException() {
//        assertThrows(
//                TaskException.class,
//                () -> taskService.delete(-1)
//        );
//    }
//
//    @Test
//    void addTaskMustAddNewTaskToDatabase() {
//        assertNull(entityManager.find(Task.class, 3));
//
//        taskService.add(new TaskAddRequest("jump", true));
//
//        assertNotNull(entityManager.find(Task.class, 3));
//    }
//}