package com.toylibrary.exception;

public class RentalNotFoundException extends RuntimeException{

    public RentalNotFoundException(Long rentalId){
        super("Rental with ID: "+ rentalId + " Dose Not exist!!");
    }
    
}
