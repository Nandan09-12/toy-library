package com.toylibrary.exception;

public class ToyNotFoundException extends RuntimeException {
    public ToyNotFoundException( Long id) {
        super("The Toy with id no. "+ id + " Dose not exist");
    }
}
