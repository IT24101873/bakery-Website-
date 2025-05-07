package com.bakery.BakeryWebsite.controllers;


import com.bakery.BakeryWebsite.models.User;
import com.bakery.BakeryWebsite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
//@CrossOrigin(origins = "http://127.0.0.1:5500")
//@CrossOrigin(origins = "http://localhost:8080")
@CrossOrigin(origins = "*")

public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return userService.createUser(user.getUsername(), user.getPassword(), user.getRole());
    }

    @PutMapping("/{userId}")
    public User updateUser(@RequestBody User user, @PathVariable long userId) {
        String password = user.getPassword();
        if (password != null && password.isBlank()) {
            password = null; // Don't update password if blank
        }
        return userService.updateUser(userId, user.getUsername(), password, user.getRole());
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable long userId) {
        userService.deleteUser(userId);
        return "User with ID " + userId + " deleted successfully";
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody User user) {
        User authenticatedUser = userService.authenticateUser(user.getUsername(), user.getPassword());
        if (authenticatedUser == null) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        return authenticatedUser;
    }
}