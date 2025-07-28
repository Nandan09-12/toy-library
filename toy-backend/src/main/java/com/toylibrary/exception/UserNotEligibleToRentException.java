package com.toylibrary.exception;

public class UserNotEligibleToRentException extends RuntimeException {
    public UserNotEligibleToRentException(Long userId) {
        super("User with ID " + userId + " is not eligible to rent toys.");
    }
}
