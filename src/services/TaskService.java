package services;

import java.util.List;

import models.Task;
import persistance.TaskDAO;
import persistance.impl.TaskDaoImpl;

public class TaskService {

    private TaskDAO taskDAO;

    public TaskService() {
        this.taskDAO = new TaskDaoImpl();
    }

    public void createTask(Task task) {
        this.taskDAO.createTask(task);
    }

    public Task getTaskByTitle(String task_title) {
        return taskDAO.getTaskByTitle(task_title);
    }

    public void updateTask(Task new_task) {
        taskDAO.updateTask(new_task);
    }

    public void deleteTask(Task task) {
        taskDAO.deleteTaskByTitle(task.getTitle());
    }

    public List<Task> getTasks() {
        return taskDAO.getTasks();

    }

    public void closeConnection() {
        taskDAO.closeConnection();
    }
}
