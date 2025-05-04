package com.bakery.BakeryWebsite.controllers;




import com.bakery.BakeryWebsite.models.User;
import com.bakery.BakeryWebsite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://127.0.0.1:5500") // Adjust for your front-end URL
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    // Get user by ID
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable long userId) {
        return userService.getUserById(userId);
    }


    // Create a new user
    @PostMapping
    public User saveUser(@RequestBody User user) {
        return userService.createUser(user.getUsername(), user.getPassword(), user.getRole());
    }

    // Update user
    @PutMapping("/{userId}")
    public User updateUser(@RequestBody User user, @PathVariable int userId) {
        return userService.updateUser(userId, user.getUsername(), user.getPassword(), user.getRole());
    }

    // Delete user
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
        return "User with ID " + userId + " deleted successfully";
    }
    // Login user (simplified)
    @PostMapping("/login")
    public User loginUser(@RequestBody User user) {
        User authenticatedUser = userService.authenticateUser(user.getUsername(), user.getPassword());
        if (authenticatedUser == null) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        return authenticatedUser;
    }
}







