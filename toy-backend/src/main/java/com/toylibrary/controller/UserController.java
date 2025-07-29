package com.toylibrary.controller;

import com.toylibrary.dto.UserDTO;
import com.toylibrary.dto.UserMapper;
import com.toylibrary.dto.UserRegisterRequestDTO;
import com.toylibrary.model.MemberType;
import com.toylibrary.model.User;
import com.toylibrary.service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(UserMapper.toDTO(user), HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserRegisterRequestDTO user) {
        User savedUser = userService.registerUser(user);
        return new ResponseEntity<>(UserMapper.toDTO(savedUser), HttpStatus.CREATED);
    }

 
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers()
            .stream()
            .map(UserMapper::toDTO)
            .collect(Collectors.toList());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    
    @GetMapping("/email")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam String email) {
        User user = userService.findByEmail(email);
        return new ResponseEntity<>(UserMapper.toDTO(user), HttpStatus.OK);
    }

   
    @GetMapping("/{id}/available-points")
    public ResponseEntity<Integer> getAvailablePoints(@PathVariable Long id) {
        int points = userService.getAvailablePoints(id);
        return ResponseEntity.ok(points);
    }

  
    @GetMapping("/{id}/is-admin")
    public ResponseEntity<Boolean> isAdmin(@PathVariable Long id) {
        return ResponseEntity.ok(userService.isAdmin(id));
    }

  
    @GetMapping("/{id}/member-type")
    public ResponseEntity<MemberType> getMemberType(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getMemberType(id));
    }
}
