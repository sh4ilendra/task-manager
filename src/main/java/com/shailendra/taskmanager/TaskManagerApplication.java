package com.shailendra.taskmanager;


import com.shailendra.taskmanager.Enums.Status;
import com.shailendra.taskmanager.model.Task;
import com.shailendra.taskmanager.model.User;
import com.shailendra.taskmanager.service.TaskManager;
import com.shailendra.taskmanager.service.TaskManagerImpl;
import com.shailendra.taskmanager.service.UserManager;
import com.shailendra.taskmanager.service.UserManagerImpl;
import com.shailendra.taskmanager.utility.InitialiseUtils;


public class TaskManagerApplication {

	public static void main(String[] args) {
		TaskManager taskManager = new TaskManagerImpl();
		UserManager userManager = new UserManagerImpl();

		User user1 = userManager.createUser("Shailendra");
		User user2 = userManager.createUser("Roopesh");

		Task task1 = InitialiseUtils.createTask("Add feature 1",user1,
				"random desc", InitialiseUtils.convertDate("2024-12-15"));
		Task task2 = InitialiseUtils.createTask("Add feature 1",user2,
				"random desc", InitialiseUtils.convertDate("2024-12-18"));
		Task task3 = InitialiseUtils.createTask("Add feature 1",user1,
				"random desc", InitialiseUtils.convertDate("2024-12-22"));
		Task task4 = InitialiseUtils.createTask("Add feature 1",user2,
				"random desc", InitialiseUtils.convertDate("2024-12-28"));
		Task task5 = InitialiseUtils.createTask("Add feature 1",user1,
				"random desc", InitialiseUtils.convertDate("2024-12-21"));

		taskManager.addTask(task1);
		taskManager.addTask(task2);
		taskManager.addTask(task3);
		taskManager.addTask(task4);
		taskManager.addTask(task5);

		System.out.println(taskManager.listTasks(user1.getUserId(), Status.CREATED,InitialiseUtils.convertDate("2024-12-22")));
		System.out.println(taskManager.getStatistics(user1.getUserId(),
				InitialiseUtils.convertDate("2024-11-13"),
				InitialiseUtils.convertDate("2024-12-22")));
		System.out.println(taskManager.getActivityLog(user1.getUserId(),
				InitialiseUtils.convertDate("2024-11-13"),
				InitialiseUtils.convertDate("2024-12-22")));
		task5.setStatus(Status.COMPLETED);
		taskManager.modifyTask(task5);

		System.out.println(taskManager.listTasks(user1.getUserId(), Status.CREATED,InitialiseUtils.convertDate("2024-12-22")));
		System.out.println(taskManager.getStatistics(user1.getUserId(),
				InitialiseUtils.convertDate("2024-11-13"),
				InitialiseUtils.convertDate("2024-12-22")));
		System.out.println(taskManager.getActivityLog(user1.getUserId(),
				InitialiseUtils.convertDate("2024-11-13"),
				InitialiseUtils.convertDate("2024-12-22")));

		taskManager.removeTask(task3.getTaskId());

		System.out.println(taskManager.listTasks(user1.getUserId(), Status.CREATED,InitialiseUtils.convertDate("2024-12-22")));
		System.out.println(taskManager.getStatistics(user1.getUserId(),
				InitialiseUtils.convertDate("2024-11-13"),
				InitialiseUtils.convertDate("2024-12-22")));
		System.out.println(taskManager.getActivityLog(user1.getUserId(),
				InitialiseUtils.convertDate("2024-11-13"),
				InitialiseUtils.convertDate("2024-12-22")));
	}

}
