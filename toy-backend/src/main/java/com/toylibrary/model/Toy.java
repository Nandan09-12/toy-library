package com.toylibrary.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "toys")
public class Toy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private int pointCost;

    @Column(nullable = false)
    private boolean isRented = false;

    // Owning user (who listed the toy)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    @JsonIgnore
    private User owner;

    // Rental history
    @OneToMany(mappedBy = "toy", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private List<Rental> rentals = new ArrayList<>();

    
    public Toy(String name, String description, int pointCost, User owner) {
        this.name = name;
        this.description = description;
        this.pointCost = pointCost;
        this.isRented = false;
        this.owner = owner;
    }

    // Bi-directional helper methods
    public void addRental(Rental rental) {
        this.rentals.add(rental);
        rental.setToy(this);
    }

    public void removeRental(Rental rental) {
        this.rentals.remove(rental);
        rental.setToy(null);
    }
}
