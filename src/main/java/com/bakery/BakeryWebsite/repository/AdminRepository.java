package com.bakery.BakeryWebsite.repository;

import com.bakery.BakeryWebsite.models.Admin;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AdminRepository {
    private final String FILE_PATH = "admins.txt";

    public List<Admin> findAll() {
        List<Admin> admins = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                admins.add(Admin.fromString(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return admins;
    }

    public Admin findById(Long id) {
        return findAll().stream()
                .filter(admin -> admin.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void save(Admin admin) {
        List<Admin> admins = findAll();
        admins.removeIf(a -> a.getId().equals(admin.getId())); // Remove existing admin if updating
        admins.add(admin);
        writeToFile(admins);
    }

    public void delete(Long id) {
        List<Admin> admins = findAll();
        admins.removeIf(admin -> admin.getId().equals(id));
        writeToFile(admins);
    }

    private void writeToFile(List<Admin> admins) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Admin admin : admins) {
                writer.write(admin.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}