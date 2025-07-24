package com.toylibrary.repository;

import com.toylibrary.model.Toy;
import com.toylibrary.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToyRepository extends JpaRepository<Toy, Long> {

    // 1. Get all available toys that are not rented
    List<Toy> findByIsRentedFalse();

    // 2. Get all toys listed by a specific owner (member)
    List<Toy> findByOwner(User owner);
}
