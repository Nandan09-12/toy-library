package com.toylibrary.dto;

import com.toylibrary.model.Rental;

public class RentalMapper {

    public static RentalDTO toDTO(Rental rental) {
        return new RentalDTO(
            rental.getId(),
            rental.getToy().getId(),
            rental.getToy().getName(),
            rental.getRenter().getId(),
            rental.getRenter().getName(),
            rental.getStartDate(),
            rental.getEndDate(),
            rental.isReturned(),
            rental.getReturnDate()
        );
    }
}
