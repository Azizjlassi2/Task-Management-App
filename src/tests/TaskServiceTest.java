package tests;

import models.Task;

import services.TaskService;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.jupiter.api.*;

public class TaskServiceTest {

    private static TaskService service;
    private static Task task;

    @BeforeAll
    static void setUp() {
        task = new Task("task title", "task test");

        TaskServiceTest.service = new TaskService();

    }

    @Test
    void testCreateTask() {

        service.createTask(task);
        assertNotNull(task.getTitle());

    }

    @Test
    void testGetTaskByName() {
        testCreateTask();
        Task new_task = service.getTaskByTitle(task.getTitle());
        assertEquals(null, task.getTitle(), new_task.getTitle());

    }

    @Test
    void testGetTasks() {
        ArrayList<Task> tasks = (ArrayList<Task>) service.getTasks();
        assertEquals(tasks.size(), service.getTasks().size());

    }

    @Test
    void testUpdateTask() {
        task.setTitle("task title modified");
        service.updateTask(task);

    }

    @Test
    void testDeleteTask() {
        service.deleteTask(task);

    }

}
