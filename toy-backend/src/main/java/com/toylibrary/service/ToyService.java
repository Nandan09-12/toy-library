package com.toylibrary.service;

import com.toylibrary.model.Toy;
import java.util.List;

public interface ToyService {
    Toy createToy(Toy toy, Long ownerId);
    Toy getToyById(Long toyId);
    List<Toy> getAvailableToys();
    List<Toy> getToysByOwner(Long ownerId);
    void deleteToy(Long toyId);
    List<Toy> getAllToys();
    Toy updateToy(Long toyId, Toy updatedToy, Long ownerId);
    Toy updateToy(Toy toy);
}

