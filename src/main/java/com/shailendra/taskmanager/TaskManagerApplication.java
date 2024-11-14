package com.shailendra.taskmanager;


import com.shailendra.taskmanager.enums.Status;
import com.shailendra.taskmanager.model.Task;
import com.shailendra.taskmanager.model.User;
import com.shailendra.taskmanager.service.TaskManager;
import com.shailendra.taskmanager.service.TaskManagerImpl;
import com.shailendra.taskmanager.service.UserManager;
import com.shailendra.taskmanager.service.UserManagerImpl;
import com.shailendra.taskmanager.utility.InitialiseUtils;

import java.util.List;
import java.util.stream.Collectors;


public class TaskManagerApplication {

	public static void main(String[] args) {
		TaskManager taskManager = new TaskManagerImpl();
		UserManager userManager = new UserManagerImpl();

		User user1 = userManager.createUser("Shailendra");
		User user2 = userManager.createUser("Roopesh");

		Task task1 = InitialiseUtils.createTask("Add feature 1",user1,
				"random desc 1", InitialiseUtils.convertDate("2024-12-15"));
		Task task2 = InitialiseUtils.createTask("Add feature 2",user2,
				"random desc 2", InitialiseUtils.convertDate("2024-12-18"));
		Task task3 = InitialiseUtils.createTask("Add feature 3",user1,
				"random desc 3", InitialiseUtils.convertDate("2024-12-22"));
		Task task4 = InitialiseUtils.createTask("Add feature 4",user2,
				"random desc 4", InitialiseUtils.convertDate("2024-12-28"));
		Task task5 = InitialiseUtils.createTask("Add feature 5",user1,
				"random desc 5", InitialiseUtils.convertDate("2024-12-21"));

		taskManager.addTask(task1);
		taskManager.addTask(task2);
		taskManager.addTask(task3);
		taskManager.addTask(task4);
		taskManager.addTask(task5);

		// Post task creation
		printUserDetails(taskManager, user1.getUserId());

		// Post Completion of a task, modify
		task5.setStatus(Status.COMPLETED);
		task5.setCompletedTime(InitialiseUtils.convertDate("2024-11-14"));
		taskManager.modifyTask(task5);
		printUserDetails(taskManager, user1.getUserId());

		// Post removal of a task
		taskManager.removeTask(task3.getTaskId());
		printUserDetails(taskManager, user1.getUserId());
	}

	private static void printUserDetails(TaskManager taskManager, String userId){
		List<Task> listTasksBasedOnStatus = taskManager.listTasks(userId, Status.CREATED,InitialiseUtils.convertDate("2024-12-22"));
		List<String> taskNames = listTasksBasedOnStatus.stream()
				.map(Task::getTaskName)
				.collect(Collectors.toList());

		System.out.println("Task Names: " + taskNames);
		System.out.println(taskManager.getStatistics(userId,
				InitialiseUtils.convertDate("2024-11-13"),
				InitialiseUtils.convertDate("2024-12-22")));
		System.out.println(taskManager.getActivityLog(userId,
				InitialiseUtils.convertDate("2024-11-13"),
				InitialiseUtils.convertDate("2024-12-22")));
		System.out.println();
	}

}
