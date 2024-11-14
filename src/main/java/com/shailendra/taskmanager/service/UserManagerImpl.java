package com.shailendra.taskmanager.service;

import com.shailendra.taskmanager.model.User;
import com.shailendra.taskmanager.repository.UserRepository;


public class UserManagerImpl implements UserManager{

    private UserRepository userRepository;

    public UserManagerImpl(){
        userRepository = new UserRepository();
    }

    @Override
    public User createUser(String userName) { // Taking userName as input
        return userRepository.createUser(userName);
    }


    @Override
    public User getUser(String userId) {
        return userRepository.getUser(userId);
    }
}
