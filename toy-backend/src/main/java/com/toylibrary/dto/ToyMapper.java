package com.toylibrary.dto;

import com.toylibrary.model.Toy;

public class ToyMapper {

    public static ToyDTO toDTO(Toy toy) {
        return new ToyDTO(
            toy.getId(),
            toy.getName(),
            toy.getDescription(),
            toy.getPointCost(),
            toy.isRented(),
            toy.getOwner().getId(),
            toy.getOwner().getName()
        );
    }
}
