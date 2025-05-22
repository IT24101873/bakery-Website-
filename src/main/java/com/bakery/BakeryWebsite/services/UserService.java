package com.bakery.BakeryWebsite.services;



import com.bakery.BakeryWebsite.models.RegularUser;
import com.bakery.BakeryWebsite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create a new user
    public RegularUser createUser(String username, String password, String email) {
        if (username == null || password == null) {
            throw new IllegalArgumentException("Invalid user details");
        }
        try {
            userRepository.findByUsername(username);
            throw new IllegalArgumentException("Username already exists");
        } catch (IllegalArgumentException e) {
            if (!e.getMessage().contains("User not found")) {
                throw e; // Re-throw if some other exception occurred
            }
        }

        RegularUser user = new RegularUser();
        user.setUsername(username);
        user.setPassword(password); // Storing as plain text (ensure hashing in production)
        user.setEmail(username + "@gmail.com");
        return userRepository.save(user);
    }

    // Get all users
    public List<RegularUser> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by ID
    public RegularUser getUserById(long id) {
        return userRepository.findById(id);
    }

    // Update user details
    public RegularUser updateUser(long id, String username, String password, String email) {
        RegularUser user = userRepository.findById(id);
        if (username != null) user.setUsername(username);
        if (password != null) user.setPassword(password);
        if (user.getEmail() == null) user.setEmail(username);
        return userRepository.save(user); // Save the updated user
    }

    // Delete user by ID
    public void deleteUser(long id) {
        userRepository.findById(id); // Check if user exists
        userRepository.deleteById(id); // Delete user
    }

    // Authenticate user with username and password
    public RegularUser authenticateUser(String username, String password) {
        RegularUser user = userRepository.findByUsername(username);

        if (user == null) {
            throw new IllegalArgumentException("User does not exist with username: " + username);
        }

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid password for user: " + username);
        }

        return user;
    }
}




