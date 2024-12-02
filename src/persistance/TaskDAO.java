package persistance;

import java.util.List;

import models.Task;

public interface TaskDAO {

    public List<Task> getTasks();

    public Task getTaskByTitle(String title);

    public void createTask(Task task);

    public void updateTask(Task task);

    public void deleteTaskByTitle(String title);

    public void closeConnection();

}