package com.toylibrary.service;

import java.util.List;
import java.util.Optional;

import com.toylibrary.model.User;

public interface UserService {
    User registerUser(User user);
    User getUserById(Long id);
    //List<User> getAllUsers();
    //void deleteUser(Long id);  
} 
