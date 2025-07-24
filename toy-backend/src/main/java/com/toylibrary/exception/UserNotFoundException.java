package com.toylibrary.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException( Long id) {
        super("The user with id no. "+ id + " Dose not exist");
    }
}
