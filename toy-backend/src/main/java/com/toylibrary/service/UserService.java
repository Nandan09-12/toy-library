package com.toylibrary.service;

import java.util.List;
import java.util.Optional;

import com.toylibrary.dto.UserRegisterRequestDTO;
import com.toylibrary.model.MemberType;
import com.toylibrary.model.User;

public interface UserService {
    User registerUser(UserRegisterRequestDTO user);
    User getUserById(Long id);
    List<User> getAllUsers();
    void deleteUser(Long id);  
    User findByEmail(String email);
    boolean hasEnoughPoints(Long userId, int pointCost);
    void deductPoints(Long userId, int pointCost);
    void refundPoints(Long userId, int pointCost);
    int getAvailablePoints(Long userId);
    boolean isAdmin(Long userId);
    MemberType getMemberType(Long userId);
}