package com.bakery.BakeryWebsite.repository;

import com.bakery.BakeryWebsite.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import java.io.File;
import java.util.*;

@Repository
public class UserRepository {

    // Read all users from file
    public List<User> findAll() {
        File file = new File("users.txt");
        ObjectMapper objectMapper = new ObjectMapper();

        List<User> users = new ArrayList<>();
        try {
            if (file.exists()) {
                users = objectMapper.readValue(file,
                        objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    // Save a user to file
    public User save(User user) {
        List<User> users = findAll();
        if (user.getId() == 0) { // New user, assign a new ID
            int currentId = users.stream().mapToInt(User::getId).max().orElse(0) + 1;
            user.setId(currentId);
            users.add(user);
        } else { // Existing user, update it
            boolean userExists = false;
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getId() == user.getId()) {
                    users.set(i, user); // Replace the existing user
                    userExists = true;
                    break;
                }
            }
            if (!userExists) {
                users.add(user); // If ID doesn't exist, add as new (optional, depending on your logic)
            }
        }
        saveToFile(users);
        return user;
    }

    // Find user by ID
    public User findById(long id) {
        List<User> users = findAll();
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
    }

    // Delete user by ID
    public void deleteById(long id) {
        List<User> users = findAll();
        users.removeIf(user -> user.getId() == id);
        saveToFile(users);
    }

    // Save the list of users to the file
    private void saveToFile(List<User> users) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("users.txt"), users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Find user by username
    public User findByUsername(String username) {
        List<User> users = findAll();
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found with username: " + username));
    }

}
