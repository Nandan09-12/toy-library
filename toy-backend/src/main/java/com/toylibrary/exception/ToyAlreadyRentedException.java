package com.toylibrary.exception;

public class ToyAlreadyRentedException extends RuntimeException {
    public ToyAlreadyRentedException(Long toyId) {
        super("Toy with ID " + toyId + " is already rented.");
    }
}
