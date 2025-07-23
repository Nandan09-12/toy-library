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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;  // MEMBER or ADMIN

    @Column
    @Enumerated(EnumType.STRING)
    private MemberType memberType;  // REGULAR or GOLD (nullable for admins)

    @Column
    private int monthlyPoints;   // 400 or 800 based on memberType

    @Column
    private int usedPoints;      // Track points currently spent

    // Toys owned by this user
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private List<Toy> toys = new ArrayList<>();

    // Rentals made by this user
    @OneToMany(mappedBy = "renter", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private List<Rental> rentals = new ArrayList<>();

    public User(String name, String email, String password, Role role, MemberType memberType, int monthlyPoints) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.role = role;
    this.memberType = memberType;
    this.monthlyPoints = monthlyPoints;
    this.usedPoints = 0;
    }

    // Bi-directional relationship helpers

    public void addToy(Toy toy) {
        this.toys.add(toy);
        toy.setOwner(this);
    }

    public void removeToy(Toy toy) {
        this.toys.remove(toy);
        toy.setOwner(null);
    }

    public void addRental(Rental rental) {
        this.rentals.add(rental);
        rental.setRenter(this);
    }

    public void removeRental(Rental rental) {
        this.rentals.remove(rental);
        rental.setRenter(null);
    }


}
