package com.shailendra.taskmanager.repository;

import com.shailendra.taskmanager.enums.Status;
import com.shailendra.taskmanager.model.Task;

import java.util.*;
import java.util.stream.Collectors;

public class TaskRepository {
    private Map<String, List<Task>> userToTasks = new HashMap<>();
    private Map<String, Task> tasks = new HashMap<>();
    private Map<String, Task> deletedTasks = new HashMap<>();

    public void addTask(Task task) {
        if (userToTasks.containsKey(task.getUser().getUserId())) {
            userToTasks.get(task.getUser().getUserId()).add(task);
        } else{
            userToTasks.put(task.getUser().getUserId(), new ArrayList<>(Arrays.asList(task)));
        }
        tasks.put(task.getTaskId(), task);
    }

    public Task getTask(String taskId) {
        return tasks.get(taskId);
    }

    public void modifyTask(Task updatedTask) {
        String taskId = updatedTask.getTaskId();
        if (tasks.containsKey(taskId)) {
            Task existingTask = tasks.get(taskId);

            // Update the task in the tasks map
            tasks.put(taskId, updatedTask);

            // Update the task in the userToTasks map (Important!)
            List<Task> userTasks = userToTasks.get(existingTask.getUser().getUserId());
            // Check for null before removing from the list in case the user is null or the mapping is missing
            if (userTasks != null) {
                userTasks.remove(existingTask);
                userTasks.add(updatedTask);
            }
        } else {
            throw new IllegalArgumentException("Task with ID " + taskId + " not found.");
        }
    }

    public void removeTask(String taskId) {
        if (tasks.containsKey(taskId)) {
            Task taskToRemove = tasks.get(taskId);
            taskToRemove.setStatus(Status.DELETED);
            deletedTasks.put(taskId,taskToRemove);
            tasks.remove(taskId);

            // Remove from userToTasks as well
            String userId = taskToRemove.getUser().getUserId();  // Assuming user is never null
            if (userToTasks.containsKey(userId)) { // Added null check for userId
                List<Task> userTasks = userToTasks.get(userId);
                userTasks.remove(taskToRemove); // Use remove to handle object equality

                // Remove the user's entry if their task list becomes empty
                if (userTasks.isEmpty()) {
                    userToTasks.remove(userId);
                }
            }


        }
    }

    public List<Task> listTasks(String userId, Status status, Date dueDateExceeded) {
        List<Task> allTasks = userToTasks.getOrDefault(userId, Collections.emptyList());

        return allTasks.stream()
                .filter(task -> status == null || task.getStatus() == status)
                .filter(task -> dueDateExceeded == null || task.getDueDate() != null && task.getDueDate().before(dueDateExceeded))
                .collect(Collectors.toList());
    }

    public Map<Status, Long> getStatistics(String userId, Date startDate, Date endDate) {
        List<Task> allTasks = userToTasks.getOrDefault(userId, Collections.emptyList());
        int userDeletedTask = 0;
        for(Task task : deletedTasks.values()){
            if(task.getUser().getUserId().equals(userId)){
                userDeletedTask++;
            }
        }

        // Filter tasks based on the provided time period (if provided)
        if (startDate != null && endDate != null) {
            allTasks = allTasks.stream()
                    .filter(task -> task.getCreatedTime().after(startDate) && task.getCreatedTime().before(endDate))
                    .toList();
        }
        Map<Status, Long> statusCounts = allTasks.stream().collect(Collectors.groupingBy(Task::getStatus, Collectors.counting()));
        statusCounts.put(Status.DELETED, (long) userDeletedTask);
        return statusCounts;

    }

    public List<String> getActivityLog(String userId, Date startDate, Date endDate) {
        List<Task> allTasks = userToTasks.getOrDefault(userId, Collections.emptyList());
        List<Task> deletedTask = new ArrayList<>();

        for(Task task : deletedTasks.values()){
            if(task.getUser().getUserId().equals(userId)){
                deletedTask.add(task);
            }
        }

        allTasks.addAll(deletedTask);

        // Filter tasks based on the provided time period (if provided)

        if (startDate != null && endDate != null) {
            allTasks = allTasks.stream()
                    .filter(task -> task.getCreatedTime().after(startDate) && task.getCreatedTime().before(endDate))
                    .toList();
        }


        return allTasks.stream()
                .map(task -> "Task '" + task.getTaskName() + "' (ID: " + task.getTaskId() + ") changed to " + task.getStatus() + " on " + task.getCompletedTime())
                .collect(Collectors.toList());
    }
}
