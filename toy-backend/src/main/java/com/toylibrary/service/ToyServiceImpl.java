package com.toylibrary.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.toylibrary.exception.ToyNotFoundException;
import com.toylibrary.exception.UserNotAllowedToListToyException;
import com.toylibrary.exception.UserNotFoundException;
import com.toylibrary.model.Role;
import com.toylibrary.model.Toy;
import com.toylibrary.model.User;
import com.toylibrary.repository.ToyRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ToyServiceImpl implements ToyService {

    private final ToyRepository toyRepository;
    private final UserService userService;
    
    @Override
    @Transactional
    public Toy createToy(Toy toy, Long ownerId) {
        User owner = userService.getUserById(ownerId);
        if (owner.getRole() != Role.MEMBER) {
            throw new UserNotAllowedToListToyException(owner.getId());
        }
        toy.setOwner(owner);
        return toyRepository.save(toy);
    }

    @Override
    public Toy getToyById(Long toyId) {
        Optional<Toy> toy = toyRepository.findById(toyId);
        return unwrapToy(toy, toyId);
    }

    @Override
    @Transactional
    public void deleteToy(Long toyId) {
        Toy toy = unwrapToy(toyRepository.findById(toyId), toyId);
        toyRepository.delete(toy);
    }

    @Override
    public List<Toy> getAllToys() {
        return toyRepository.findAll();
    }

    @Override
    public List<Toy> getAvailableToys() {
        return toyRepository.findByIsRentedFalse();
    }

    @Override
    public List<Toy> getToysByOwner(Long ownerId) {
        User user = userService.getUserById(ownerId);
        return toyRepository.findByOwner(user);
    }




    static Toy unwrapToy(Optional<Toy> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new ToyNotFoundException(id != null ? id : -1L);
    }

    
}
