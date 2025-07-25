package com.toylibrary.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.toylibrary.exception.MemberTypeNotApplicableException;
import com.toylibrary.exception.UserAlreadyExistsException;
import com.toylibrary.exception.UserNotFoundException;
import com.toylibrary.model.MemberType;
import com.toylibrary.model.Role;
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
            throw new UserAlreadyExistsException("Email is already registred!");
        }
        user.setUsedPoints(0);
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        return unwrapUser(user, id);
    }

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.deleteById(id);
        } else{
            throw new UserNotFoundException(id);
        }
    }

    @Override
    public User findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return unwrapUser(user, null);
    }

    @Override
    public boolean hasEnoughPoints(Long userId, int pointCost) {
        User user = unwrapUser(userRepository.findById(userId), userId);
        int availablePoints = user.getMonthlyPoints() - user.getUsedPoints();
        return availablePoints >= pointCost;
    }

    @Override
    @Transactional
    public void deductPoints(Long userId, int pointCost){
        User user = unwrapUser(userRepository.findById(userId), userId);
        if (hasEnoughPoints(userId,pointCost)){
            user.setUsedPoints(user.getUsedPoints()+pointCost);
            userRepository.save(user);
        } else throw new IllegalArgumentException("User Dose not have enough points!");
    }

    @Override
    @Transactional
    public void refundPoints(Long userId, int pointCost) {
        User user = unwrapUser(userRepository.findById(userId), userId);
        int newUsedPoints = user.getUsedPoints() - pointCost;
        if (newUsedPoints < 0) {
            newUsedPoints = 0; 
        }
        user.setUsedPoints(newUsedPoints);
        userRepository.save(user);
    }

    @Override
    public int getAvailablePoints(Long userId) {
        User user = unwrapUser(userRepository.findById(userId), userId);
        return user.getMonthlyPoints() - user.getUsedPoints();
    }

    @Override
    public boolean isAdmin(Long userId) {
        User user = unwrapUser(userRepository.findById(userId), userId);
        return user.getRole() == Role.ADMIN;
    }

    @Override
    public MemberType getMemberType(Long userId) {
        User user = unwrapUser(userRepository.findById(userId), userId);
        if (user.getRole() == Role.ADMIN) {
        throw new MemberTypeNotApplicableException("Admins don't have a member type.");
        }
        return user.getMemberType();
    }






    static User unwrapUser(Optional<User> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new UserNotFoundException(id != null ? id : -1L);
    }


}
