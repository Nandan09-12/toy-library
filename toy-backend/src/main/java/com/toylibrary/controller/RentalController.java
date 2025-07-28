package com.toylibrary.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.toylibrary.dto.RentalDTO;
import com.toylibrary.dto.RentalMapper;
import com.toylibrary.model.Rental;
import com.toylibrary.service.RentalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rentals")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

  
    @PostMapping("/rent/{userId}/{toyId}")
    public ResponseEntity<RentalDTO> rentToy(@PathVariable Long userId, @PathVariable Long toyId) {
        Rental rental = rentalService.rentToy(userId, toyId);
        return new ResponseEntity<>(RentalMapper.toDTO(rental), HttpStatus.CREATED);
    }

    
    @PostMapping("/return/{rentalId}")
    public ResponseEntity<RentalDTO> returnToy(@PathVariable Long rentalId) {
        Rental rental = rentalService.returnToy(rentalId);
        return new ResponseEntity<>(RentalMapper.toDTO(rental), HttpStatus.OK);
    }

  
    @GetMapping("/{id}")
    public ResponseEntity<RentalDTO> getRentalById(@PathVariable Long id) {
        Rental rental = rentalService.getRentalById(id);
        return new ResponseEntity<>(RentalMapper.toDTO(rental), HttpStatus.OK);
    }

    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RentalDTO>> getRentalsByUserId(@PathVariable Long userId) {
        List<RentalDTO> rentals = rentalService.getRentalsByUserId(userId)
            .stream().map(RentalMapper::toDTO).toList();
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/active")
    public ResponseEntity<List<RentalDTO>> getActiveRentalsByUser(@PathVariable Long userId) {
        List<RentalDTO> rentals = rentalService.getActiveRentalsByUserId(userId)
            .stream().map(RentalMapper::toDTO).toList();
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }

   
    @GetMapping("/all/{adminId}")
    public ResponseEntity<List<RentalDTO>> getAllRentals(@PathVariable Long adminId) {
        List<RentalDTO> rentals = rentalService.getAllRentals(adminId)
            .stream().map(RentalMapper::toDTO).toList();
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }


    @GetMapping("/active-by-toy/{toyId}")
    public ResponseEntity<List<RentalDTO>> getActiveRentalsForToy(@PathVariable Long toyId) {
        List<RentalDTO> rentals = rentalService.getActiveRentalsByToyId(toyId)
            .stream().map(RentalMapper::toDTO).toList();
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }

    @GetMapping("/toy/{toyId}/history")
    public ResponseEntity<List<RentalDTO>> getRentalHistoryByToyId(@PathVariable Long toyId) {
        List<RentalDTO> history = rentalService.getRentalHistoryByToyId(toyId)
            .stream().map(RentalMapper::toDTO).toList();
        return ResponseEntity.ok(history);
    }
}
