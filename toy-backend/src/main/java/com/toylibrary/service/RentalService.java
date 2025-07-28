package com.toylibrary.service;

import java.util.List;

import com.toylibrary.model.Rental;

public interface RentalService {
    
    Rental rentToy(Long userId, Long toyId);
    Rental returnToy(Long rentalId);
    Rental getRentalById(Long rentalId);
    List<Rental> getRentalsByUserId(Long userId);
    List<Rental> getActiveRentalsByUserId(Long userId);
    public List<Rental> getAllRentals(Long adminId);
    List<Rental> getActiveRentalsByToyId(Long toyId);
    List<Rental> getRentalHistoryByToyId(Long toyId);
}
