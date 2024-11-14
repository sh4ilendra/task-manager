package com.shailendra.taskmanager.service;

import com.shailendra.taskmanager.enums.Status;
import com.shailendra.taskmanager.model.Task;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TaskManager {

    void addTask(Task task);

    Task getTask(String taskId);

    void modifyTask(Task task);

    void removeTask(String taskId);

    List<Task> listTasks(String userId, Status status, Date dueDateExceeded );

    public Map<Status, Long> getStatistics(String userId, Date startDate, Date endDate);

    public List<String> getActivityLog(String userId, Date startDate, Date endDate);
}
