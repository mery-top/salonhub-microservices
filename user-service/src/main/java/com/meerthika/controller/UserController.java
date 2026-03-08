package com.meerthika.controller;

import com.meerthika.exception.UserException;
import com.meerthika.modal.User;
import com.meerthika.respository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/api/users")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {

        User savedUser = userRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getAllUsers() {

        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) throws Exception {

        Optional<User> otp = userRepository.findById(id);

        if (otp.isPresent()) {
            return new ResponseEntity<>(otp.get(), HttpStatus.OK);
        }

        throw new UserException("User not found with id " + id);
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id) throws Exception {

        Optional<User> otp = userRepository.findById(id);

        if (otp.isEmpty()) {
            throw new UserException("User not found with id " + id);
        }

        User existingUser = otp.get();
        existingUser.setFullName(user.getFullName());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());
        existingUser.setUsername(user.getUsername());

        User updatedUser = userRepository.save(existingUser);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) throws Exception {

        Optional<User> otp = userRepository.findById(id);

        if (otp.isEmpty()) {
            throw new UserException("User not found with id " + id);
        }

        userRepository.deleteById(id);

        return new ResponseEntity<>("User deleted Successfully!", HttpStatus.OK);
    }
}