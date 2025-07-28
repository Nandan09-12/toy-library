package com.toylibrary.exception;

public class RentalAlreadyReturnedException extends RuntimeException{
    public RentalAlreadyReturnedException(Long rentalId){
        super("The Toy with rentalID " + rentalId + " Has already been returned!");
    }
    
}
