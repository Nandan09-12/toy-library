package com.toylibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToyDTO {
    private Long id;
    private String name;
    private String description;
    private int pointCost;
    private boolean rented;
    private Long ownerId;
    private String ownerName;
}
