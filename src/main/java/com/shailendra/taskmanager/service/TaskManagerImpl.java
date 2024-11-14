package com.shailendra.taskmanager.service;

import com.shailendra.taskmanager.enums.Status;
import com.shailendra.taskmanager.model.Task;
import com.shailendra.taskmanager.repository.TaskRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class TaskManagerImpl implements TaskManager{

    private TaskRepository taskRepository;
    public TaskManagerImpl() {
        taskRepository = new TaskRepository();
    }

    @Override
    public void addTask(Task task) {
        taskRepository.addTask(task);
    }

    @Override
    public Task getTask(String taskId) {
        return taskRepository.getTask(taskId);
    }

    @Override
    public void modifyTask(Task task) {
        taskRepository.modifyTask(task);
    }

    @Override
    public void removeTask(String taskId) {
        taskRepository.removeTask(taskId);
    }

    @Override
    public List<Task> listTasks(String userId, Status status, Date dueDateExceeded) {
        return taskRepository.listTasks( userId, status, dueDateExceeded);
    }

    @Override
    public Map<Status, Long> getStatistics(String userId, Date startDate, Date endDate) {
        return taskRepository.getStatistics(userId, startDate, endDate);
    }

    @Override
    public List<String> getActivityLog(String userId, Date startDate, Date endDate) {
       return taskRepository.getActivityLog(userId, startDate, endDate);
    }


}
