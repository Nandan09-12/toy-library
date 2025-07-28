package com.toylibrary.dto;

import com.toylibrary.model.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        return new UserDTO(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getRole(),
            user.getMemberType(),
            user.getMonthlyPoints(),
            user.getUsedPoints()
        );
    }
}
