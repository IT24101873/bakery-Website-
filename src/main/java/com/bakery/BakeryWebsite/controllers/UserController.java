package com.bakery.BakeryWebsite.controllers;

import com.bakery.BakeryWebsite.models.RegularUser;
import com.bakery.BakeryWebsite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Get all users
    @GetMapping
    public List<RegularUser> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get user by ID
    @GetMapping("/{userId}")
    public RegularUser getUserById(@PathVariable long userId) {
        return userService.getUserById(userId);
    }

    // Create a new user
    @PostMapping
    public RegularUser saveUser(@RequestBody RegularUser user) {
        return userService.createUser(user.getUsername(), user.getPassword(), user.getEmail());
    }

    // Update an existing user
    @PutMapping("/{userId}")
    public RegularUser updateUser(@RequestBody RegularUser user, @PathVariable long userId) {
        return userService.updateUser(userId, user.getUsername(), user.getPassword(), user.getEmail());
    }

    // Delete a user
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable long userId) {
        userService.deleteUser(userId);
        return "User with ID " + userId + " deleted successfully";
    }

    // Login user
    @PostMapping("/login")
    public RegularUser loginUser(@RequestBody RegularUser user) {
        RegularUser authenticatedUser = userService.authenticateUser(user.getUsername(), user.getPassword());
        if (authenticatedUser == null) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        return authenticatedUser;
    }
}

