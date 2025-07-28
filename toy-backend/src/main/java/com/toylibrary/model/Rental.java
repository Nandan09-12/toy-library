package com.toylibrary.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Optional;

import com.toylibrary.exception.UserNotFoundException;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "renter_id", nullable = false)
    private User renter;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "toy_id", nullable = false)
    private Toy toy;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;  // Always = startDate + 30, calculated in service layer

    @Column(nullable = false)
    private boolean returned = false;

    @Column
    private LocalDate returnDate;  // Filled only when returned


    public Rental(User renter, Toy toy, LocalDate startDate, LocalDate endDate) {
        this.renter = renter;
        this.toy = toy;
        this.startDate = startDate;
        this.endDate = endDate;
        this.returned = false;
    }


    

}
