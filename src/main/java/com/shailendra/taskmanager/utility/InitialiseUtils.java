package com.shailendra.taskmanager.utility;

import com.shailendra.taskmanager.enums.Status;
import com.shailendra.taskmanager.model.Task;
import com.shailendra.taskmanager.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class InitialiseUtils {

    public static Task createTask(String taskName, User user, String description) {
        return createTask(taskName, user, description, null); // Call overloaded method with null due date
    }

    public static Task createTask(String taskName, User user, String description, Date dueDate) {
        Task task = new Task();
        task.setTaskId(UUID.randomUUID().toString());
        task.setTaskName(taskName);
        task.setUser(user);       // Associate task with user
        task.setDescription(description); // Set the description
        task.setStatus(Status.CREATED); // Set the initial status as OPEN.
        task.setCreatedTime(new Date());
        task.setDueDate(dueDate);  // Set the due date (can be null)
        return task;
    }

    public static Date convertDate(String dateString){
        String format = "yyyy-MM-dd";
        return createDateFromString(dateString, format);
    }
    public static Date createDateFromString(String dateString, String format) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.err.println("Invalid date format: " + dateString);
            // Handle the exception appropriately (e.g., return null, throw a custom exception)
            return null;
        }
    }
}
