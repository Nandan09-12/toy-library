package com.toylibrary.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalDTO {
    private Long rentalId;
    private Long toyId;
    private String toyName;
    private Long renterId;
    private String renterName;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean returned;
    private LocalDate returnDate;

    
}
