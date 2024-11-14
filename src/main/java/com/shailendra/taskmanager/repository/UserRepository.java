package com.shailendra.taskmanager.repository;

import com.shailendra.taskmanager.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserRepository {

    private Map<String, User> users = new HashMap<>();

    public User createUser(String userName) {
        String userId = UUID.randomUUID().toString();
        User user = new User();
        user.setUserId(userId);
        user.setUserName(userName);

        users.put(userId, user); // Store the user
        return user;
    }

    public User getUser(String userId) {
        return users.get(userId); // Retrieve from map
    }
}
