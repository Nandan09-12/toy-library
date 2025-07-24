package com.toylibrary.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.toylibrary.exception.UserAlreadyExistsException;
import com.toylibrary.exception.UserNotFoundException;
import com.toylibrary.model.User;
import com.toylibrary.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Transactional
    @Override
    public User registerUser(User user){
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if(existingUser.isPresent()){
            throw new UserAlreadyExistsException("Email is already resistered!");
        }

        user.setUsedPoints(0);

        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        return unwrapUser(user, id);
    }


    static User unwrapUser(Optional<User> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new UserNotFoundException(id);
    }


}
