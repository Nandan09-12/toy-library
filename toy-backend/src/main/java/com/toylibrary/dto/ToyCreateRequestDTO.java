package com.toylibrary.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ToyCreateRequestDTO {
    private String name;
    private String description;
    private int pointCost;
}
