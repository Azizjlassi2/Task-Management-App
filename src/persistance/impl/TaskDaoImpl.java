package persistance.impl;

import java.util.ArrayList;
import java.util.List;

import connection.DatabaseConnection;
import models.Task;
import persistance.TaskDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskDaoImpl implements TaskDAO {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public TaskDaoImpl() {
        connection = DatabaseConnection.getConnection();
    }

    @Override
    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();

        try {

            preparedStatement = connection.prepareStatement("SELECT * FROM task");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Task task = new Task(null, null);

                task.setTitle(resultSet.getString("title"));
                task.setDescription(resultSet.getString("description"));

                tasks.add(task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return tasks.isEmpty() ? null : tasks;

    }

    @Override
    public Task getTaskByTitle(String title) {

        Task task = new Task(null, null);

        try {
            preparedStatement = null;
            resultSet = null;

            preparedStatement = connection.prepareStatement("SELECT * FROM task_db.task WHERE title = ?");
            preparedStatement.setString(1, title);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) { // Vérifie s'il y a au moins une ligne de résultat
                String taskTitle = resultSet.getString("title");
                System.out.println("Task found: " + taskTitle);
            } else {
                System.out.println("No task found with title: " + title);
            }

            task.setTitle(resultSet.getString("title"));
            task.setDescription(resultSet.getString("description"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return task != null ? task : null;
    }

    @Override
    public void updateTask(Task task) {
        try {
            String sql = "UPDATE task SET description = ?,last_update = ? WHERE title = ?";
            preparedStatement = connection.prepareStatement(sql);

            // update 'last_update' field

            // Set values for the PreparedStatement
            preparedStatement.setString(1, task.getDescription());
            preparedStatement.setDate(2, new Date(new java.util.Date().getYear(), new java.util.Date().getMonth(),
                    new java.util.Date().getDay()));
            preparedStatement.setString(3, task.getTitle());

            // Execute the update
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in reverse order of creation
            try {
                if (preparedStatement != null)
                    preparedStatement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void deleteTaskByTitle(String title) {

        try {
            String sql = "DELETE FROM task WHERE title = ?";
            preparedStatement = connection.prepareStatement(sql);

            // Set the value for the PreparedStatement
            preparedStatement.setString(1, title);

            // Execute the delete
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in reverse order of creation
            try {
                if (preparedStatement != null)
                    preparedStatement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void createTask(Task task) {
        try {
            String sql = "INSERT INTO task (title, description,last_update,date_created) VALUES (?, ?,?,?)";
            preparedStatement = connection.prepareStatement(sql);

            // Set values for the PreparedStatement

            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setDate(3, new Date(task.getLast_update().getTime()));
            preparedStatement.setDate(4, new Date(task.getDate_created().getTime()));

            // Execute the insert
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in reverse order of creation
            try {
                if (preparedStatement != null)
                    preparedStatement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            System.out.println("Error when Closing Connection !");
        }
    }
}
