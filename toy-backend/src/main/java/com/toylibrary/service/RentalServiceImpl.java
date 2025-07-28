package com.toylibrary.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.toylibrary.exception.CannotRentOwnToyException;
import com.toylibrary.exception.InsufficientPointsException;
import com.toylibrary.exception.RentalAlreadyReturnedException;
import com.toylibrary.exception.RentalNotFoundException;
import com.toylibrary.exception.ToyAlreadyRentedException;
import com.toylibrary.exception.UnauthorizedAccessException;
import com.toylibrary.exception.UserNotEligibleToRentException;
import com.toylibrary.exception.UserNotFoundException;
import com.toylibrary.model.Rental;
import com.toylibrary.model.Role;
import com.toylibrary.model.Toy;
import com.toylibrary.model.User;
import com.toylibrary.repository.RentalRepository;
import com.toylibrary.repository.ToyRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService{

    private final RentalRepository rentalRepository;
    private final ToyService toyService;
    private final UserService userService;

    @Override
    @Transactional
    public Rental rentToy(Long userId, Long toyId){
        User renter = userService.getUserById(userId);
        Toy toy = toyService.getToyById(toyId);

        if (toy.getOwner().getId().equals(renter.getId())) {
            throw new CannotRentOwnToyException(toyId);
        }
        // 2. Validations
        if (toy.isRented()) {
            throw new ToyAlreadyRentedException(toyId);
        }

        if (renter.getRole() != Role.MEMBER) {
            throw new UserNotEligibleToRentException(userId);
        }

        if (!userService.hasEnoughPoints(userId, toy.getPointCost())) {
            throw new InsufficientPointsException(userId, toy.getPointCost());
        }

        // 3. Mark toy as rented
        toy.setRented(true);
        toyService.updateToy(toy);

        // 4. Deduct points
        userService.deductPoints(userId, toy.getPointCost());

        // 5. Set rental period
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(30);

        // 6. Create and save Rental
        Rental rental = new Rental(renter, toy, startDate, endDate);
        return rentalRepository.save(rental);
    }

    @Override
    @Transactional
    public Rental returnToy(Long rentalId){
        // 1. Find rental and validate
        Rental rental = unwrapRental(rentalRepository.findById(rentalId), rentalId);

        if (rental.isReturned()) {
            throw new RentalAlreadyReturnedException(rentalId);
        }

        // 2. Mark rental as returned
        rental.setReturned(true);
        rental.setReturnDate(LocalDate.now());

        // 3. Flip toy's status
        Toy toy = rental.getToy();
        toy.setRented(false);
        toyService.updateToy(toy); 

        // 4. Refund points to the user
        User renter = rental.getRenter();
        userService.refundPoints(renter.getId(), toy.getPointCost());

        // 5. Save rental
        return rentalRepository.save(rental);
    }

    @Override
    public Rental getRentalById(Long rentalId){
        return unwrapRental(rentalRepository.findById(rentalId), rentalId);
    }

    @Override
    public List<Rental> getRentalsByUserId(Long userId) {
        User user = userService.getUserById(userId); 
        return rentalRepository.findByRenter(user);
    }

    @Override
    public List<Rental> getActiveRentalsByUserId(Long userId) {
        User user = userService.getUserById(userId);
        return rentalRepository.findByRenterAndReturnedFalse(user);
    }

    @Override 
    public List<Rental> getAllRentals(Long adminId) {
        User admin = userService.getUserById(adminId);
        if (admin.getRole() != Role.ADMIN) {
            throw new UnauthorizedAccessException("Only admins can view all rentals.");
        }
        return rentalRepository.findAll();
    }

    @Override
    public List<Rental> getActiveRentalsByToyId(Long toyId) {
        Toy toy = toyService.getToyById(toyId);
        return rentalRepository.findByToyAndReturnedFalse(toy);
    }

    @Override
    public List<Rental> getRentalHistoryByToyId(Long toyId) {
        Toy toy = toyService.getToyById(toyId);
        return rentalRepository.findByToy(toy);
    }








    static Rental unwrapRental(Optional<Rental> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new RentalNotFoundException(id != null ? id : -1L);
    }


}
