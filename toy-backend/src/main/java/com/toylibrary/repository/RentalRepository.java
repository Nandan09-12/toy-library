package com.toylibrary.repository;

import com.toylibrary.model.Rental;
import com.toylibrary.model.Toy;
import com.toylibrary.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    // Get all rentals by user (renter)
    List<Rental> findByRenter(User renter);

    // Get rental history of a toy
    List<Rental> findByToy(Toy toy);

    // Optional: Active rentals by user (not yet returned)
    List<Rental> findByRenterAndReturnedFalse(User renter);

    // Optional: Active rental for a toy
    List<Rental> findByToyAndReturnedFalse(Toy toy);



}
