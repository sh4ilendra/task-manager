package com.shailendra.taskmanager.service;

import com.shailendra.taskmanager.model.User;

public interface UserManager {

    public User createUser(String userName);

    public User getUser(String userId);
}
