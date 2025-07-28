package com.toylibrary.exception;

public class InsufficientPointsException extends RuntimeException {
    public InsufficientPointsException(Long userId, int requiredPoints) {
        super("User with ID " + userId + " does not have enough points. Required: " + requiredPoints);
    }
}
