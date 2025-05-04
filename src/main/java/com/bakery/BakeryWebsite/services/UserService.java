package com.bakery.BakeryWebsite.services;


import com.bakery.BakeryWebsite.models.AdminUser;
import com.bakery.BakeryWebsite.models.CustomerUser;
import com.bakery.BakeryWebsite.models.User;
import com.bakery.BakeryWebsite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create a new user
    public User createUser(String username, String password, String role) {
        if (username == null || password == null || role == null) {
            throw new IllegalArgumentException("Invalid user details");
        }
        try {
            userRepository.findByUsername(username);
            throw new IllegalArgumentException("Username already exists");
        } catch (IllegalArgumentException e) {
            if (!e.getMessage().contains("User not found")) {
                throw e; // Re-throw if username exists
            }
        }
        User user = role.equals("ADMIN") ? new AdminUser() : new CustomerUser();
        user.setUsername(username);
        user.setPassword(password); // Plain text, no security
        user.setRole(role);
        return userRepository.save(user);
    }

    // Read all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Read user by ID
    public User getUserById(long id) {
        return userRepository.findById(id);
    }

    // Update user
    public User updateUser(long id, String username, String password, String role) {
        User user = userRepository.findById(id); // Will throw if not found
        if (username != null) user.setUsername(username);
        if (password != null) user.setPassword(password);
        if (role != null) user.setRole(role);
        return userRepository.save(user);
    }

    // Delete user
    public void deleteUser(long id) {
        userRepository.findById(id); // Will throw if not found
        userRepository.deleteById(id);
    }

    // Authenticate user
    public User authenticateUser(String username, String password) {
        try {
            User user = userRepository.findByUsername(username);
            if (user.getPassword().equals(password)) { // Plain text comparison
                return user;
            }
            return null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
