package com.bakery.BakeryWebsite.repository;

import com.bakery.BakeryWebsite.models.AdminUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AdminUserRepository {

    private final String FILE_PATH = "admins.txt";

    public List<AdminUser> findAll() {
        File file = new File(FILE_PATH);
        ObjectMapper objectMapper = new ObjectMapper();
        List<AdminUser> users = new ArrayList<>();
        try {
            if (file.exists()) {
                users = objectMapper.readValue(file,
                        objectMapper.getTypeFactory().constructCollectionType(List.class, AdminUser.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public AdminUser save(AdminUser user) {
        List<AdminUser> users = findAll();
        if (user.getId() == 0) {
            int currentId = users.stream().mapToInt(AdminUser::getId).max().orElse(0) + 1;
            user.setId(currentId);
            users.add(user);
        } else {
            boolean userExists = false;
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getId() == user.getId()) {
                    users.set(i, user);
                    userExists = true;
                    break;
                }
            }
            if (!userExists) {
                users.add(user);
            }
        }
        saveToFile(users);
        return user;
    }

    public AdminUser findById(long id) {
        List<AdminUser> users = findAll();
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Admin user not found with ID: " + id));
    }

    public void deleteById(long id) {
        List<AdminUser> users = findAll();
        users.removeIf(user -> user.getId() == id);
        saveToFile(users);
    }

    public AdminUser findByUsername(String username) {
        List<AdminUser> users = findAll();
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Admin user not found with username: " + username));
    }

    private void saveToFile(List<AdminUser> users) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            objectMapper.writeValue(new File(FILE_PATH), users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
