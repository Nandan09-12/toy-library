package com.toylibrary.exception;

public class CannotRentOwnToyException extends RuntimeException {
    public CannotRentOwnToyException(Long toyId) {
        super("You cannot rent your own toy with ID: " + toyId);
    }
}
