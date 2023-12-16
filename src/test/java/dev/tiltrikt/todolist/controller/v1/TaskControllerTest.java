package dev.tiltrikt.todolist.controller.v1;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.tiltrikt.todolist.application.TodolistApplication;
import dev.tiltrikt.todolist.exception.TaskException;
import dev.tiltrikt.todolist.model.Task;
import dev.tiltrikt.todolist.request.TaskAddRequest;
import dev.tiltrikt.todolist.request.TaskChangeRequest;
import dev.tiltrikt.todolist.service.task.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
@ContextConfiguration(classes = TodolistApplication.class)
class TaskControllerTest {

    @MockBean
    TaskService taskService;

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void onGettingAllMustReturnPayloadWithTaskList() throws Exception {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1, "work", true));
        taskList.add(new Task(2, "sleep", false));

        when(taskService.getAll()).thenReturn(taskList);

        mvc.perform(get("/v1/tasks"))
                // General assertions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errors").isEmpty())
                .andExpect(jsonPath("$.payload", hasSize(2)))

                // Task 1
                .andExpect(jsonPath("$.payload.[0].id").value(1))
                .andExpect(jsonPath("$.payload.[0].text").value("work"))
                .andExpect(jsonPath("$.payload.[0].active").value(true))

                // Task 2
                .andExpect(jsonPath("$.payload.[1].id").value(2))
                .andExpect(jsonPath("$.payload.[1].text").value("sleep"))
                .andExpect(jsonPath("$.payload.[1].active").value(false));
    }

    @Test
    void onGettingAllWithEmptyListMustReturnPayloadWithEmptyTaskList() throws Exception {
        List<Task> taskList = new ArrayList<>();
        when(taskService.getAll()).thenReturn(taskList);

        mvc.perform(get("/v1/tasks"))
                // General assertions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errors").isEmpty())
                .andExpect(jsonPath("$.payload", hasSize(0)));
    }

    @Test
    void onGettingAllWithUnknownRuntimeExceptionMustReturnInternalServerError() throws Exception {
        when(taskService.getAll()).thenThrow(new RuntimeException());

        Map<String, String> map = new TreeMap<>();
        map.put("exception", "Internal server error");

        mvc.perform(get("/v1/tasks"))
                // General assertions
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errors", equalTo(map)))
                .andExpect(jsonPath("$.payload").isEmpty());
    }

    @Test
    void onGettingActiveMustReturnPayloadWithTaskList() throws Exception {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1, "work", true));
        taskList.add(new Task(2, "sleep", true));

        when(taskService.getByActive(true)).thenReturn(taskList);

        mvc.perform(get("/v1/tasks/active"))
                // General assertions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errors").isEmpty())
                .andExpect(jsonPath("$.payload", hasSize(2)))

                // Task 1
                .andExpect(jsonPath("$.payload.[0].id").value(1))
                .andExpect(jsonPath("$.payload.[0].text").value("work"))
                .andExpect(jsonPath("$.payload.[0].active").value(true))

                // Task 2
                .andExpect(jsonPath("$.payload.[1].id").value(2))
                .andExpect(jsonPath("$.payload.[1].text").value("sleep"))
                .andExpect(jsonPath("$.payload.[1].active").value(true));
    }

    @Test
    void onGettingFinishedMustReturnPayloadWithTaskList() throws Exception {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1, "work", false));
        taskList.add(new Task(2, "sleep", false));

        when(taskService.getByActive(false)).thenReturn(taskList);

        mvc.perform(get("/v1/tasks/finished"))
                // General assertions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errors").isEmpty())
                .andExpect(jsonPath("$.payload", hasSize(2)))

                // Task 1
                .andExpect(jsonPath("$.payload.[0].id").value(1))
                .andExpect(jsonPath("$.payload.[0].text").value("work"))
                .andExpect(jsonPath("$.payload.[0].active").value(false))

                // Task 2
                .andExpect(jsonPath("$.payload.[1].id").value(2))
                .andExpect(jsonPath("$.payload.[1].text").value("sleep"))
                .andExpect(jsonPath("$.payload.[1].active").value(false));
    }

    @Test
    void onAddingTaskWithTextAndActiveMustBeSuccess() throws Exception {
        TaskAddRequest task = new TaskAddRequest("work", true);
        String taskJson = mapper.writeValueAsString(task);

        mvc.perform(post("/v1/tasks/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errors").isEmpty())
                .andExpect(jsonPath("$.payload").isEmpty());
    }

    @Test
    void onAddingTaskWithTextOnlyMustBeSuccess() throws Exception {
        TaskAddRequest task = new TaskAddRequest();
        task.setText("work");
        String taskJson = mapper.writeValueAsString(task);

        mvc.perform(post("/v1/tasks/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errors").isEmpty())
                .andExpect(jsonPath("$.payload").isEmpty());
    }

    @Test
    void onAddingTaskWithoutTextMustReturnError() throws Exception {
        TaskAddRequest task = new TaskAddRequest();
        String taskJson = mapper.writeValueAsString(task);
        Map<String, String> map = new TreeMap<>();
        map.put("text", "Text is mandatory");

        mvc.perform(post("/v1/tasks/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errors").isMap())
                .andExpect(jsonPath("$.errors", equalTo(map)))
                .andExpect(jsonPath("$.payload").isEmpty());

        verify(taskService, times(0)).add(task);
    }

    @Test
    void onAddingTaskWithoutTextMustNotCallTaskService() throws Exception {
        TaskAddRequest task = new TaskAddRequest();
        String taskJson = mapper.writeValueAsString(task);

        mvc.perform(post("/v1/tasks/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(taskJson));

        verify(taskService, times(0)).add(task);
    }

    @Test
    void onUpdatingTaskWithIdThatExistsMustBeSuccess() throws Exception {
        TaskChangeRequest task = new TaskChangeRequest("work", true);
        String taskJson = mapper.writeValueAsString(task);

        mvc.perform(put("/v1/tasks/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errors").isEmpty())
                .andExpect(jsonPath("$.payload").isEmpty());
    }

    @Test
    void onUpdatingTaskWithIdThatDoesntExistErrorMustBeReturned() throws Exception {
        TaskChangeRequest task = new TaskChangeRequest("work", true);
        String taskJson = mapper.writeValueAsString(task);

        doThrow(new TaskException("task with id -1 wasn't found"))
                .when(taskService).update(-1, new TaskChangeRequest("work", true));

        Map<String, String> map = new TreeMap<>();
        map.put("exception", "task with id -1 wasn't found");

        mvc.perform(put("/v1/tasks/update/-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errors", equalTo(map)))
                .andExpect(jsonPath("$.payload").isEmpty());
    }

    @Test
    void onUpdatingTaskWithEmptyChangeListErrorMustBeReturned() throws Exception {
        TaskChangeRequest task = new TaskChangeRequest();
        String taskJson = mapper.writeValueAsString(task);

        Map<String, String> map = new TreeMap<>();
        map.put("atLeastOneVariableNotEmpty", "At least one field must be not empty");

        mvc.perform(put("/v1/tasks/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errors", equalTo(map)))
                .andExpect(jsonPath("$.payload").isEmpty());
    }

    @Test
    void onDeletingTaskWithIdThatExistsMustBeSuccess() throws Exception {
        mvc.perform(delete("/v1/tasks/delete/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errors").isEmpty())
                .andExpect(jsonPath("$.payload").isEmpty());
    }

    @Test
    void onDeletingTaskWithIdThatDoesntExistErrorMustBe() throws Exception {
        doThrow(new TaskException("task with id -1 wasn't found")).when(taskService).delete(-1);
        Map<String, String> map = new TreeMap<>();
        map.put("exception", "task with id -1 wasn't found");

        mvc.perform(delete("/v1/tasks/delete/-1"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errors", equalTo(map)))
                .andExpect(jsonPath("$.payload").isEmpty());
    }
}