package com.toylibrary.exception;


public class UserNotAllowedToListToyException extends RuntimeException {
    public UserNotAllowedToListToyException(Long userId) {
        super("User with ID " + userId + " is not allowed to list a toy. Only MEMBERS can list toys.");
    }
}
