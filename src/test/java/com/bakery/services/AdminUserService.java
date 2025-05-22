package com.bakery.BakeryWebsite.services;

import com.bakery.BakeryWebsite.models.AdminUser;
import com.bakery.BakeryWebsite.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserService {

    @Autowired
    private AdminUserRepository adminUserRepository;

    public AdminUser createAdminUser(String username, String password, String adminLevel) {
        if (username == null || password == null || adminLevel == null) {
            throw new IllegalArgumentException("Invalid admin user details");
        }
        try {
            adminUserRepository.findByUsername(username);
            throw new IllegalArgumentException("Username already exists");
        } catch (IllegalArgumentException e) {
            if (!e.getMessage().contains("Admin user not found")) {
                throw e;
            }
        }
        AdminUser user = new AdminUser();
        user.setUsername(username);
        user.setPassword(password); // Plain text, as per original
        user.setAdminLevel(adminLevel);
        user.setEmail(username);
        return adminUserRepository.save(user);
    }

    public List<AdminUser> getAllAdminUsers() {
        return adminUserRepository.findAll();
    }

    public AdminUser getAdminUserById(long id) {
        return adminUserRepository.findById(id);
    }

    public AdminUser updateAdminUser(long id, String username, String password, String adminLevel, String email) {
        AdminUser user = adminUserRepository.findById(id);
        if (username != null) user.setUsername(username);
        if (password != null) user.setPassword(password);
        if (adminLevel != null) user.setAdminLevel(adminLevel);
        if (user.getEmail() == null) user.setEmail(username);
        return adminUserRepository.save(user);
    }

    public void deleteAdminUser(long id) {
        adminUserRepository.findById(id);
        adminUserRepository.deleteById(id);
    }

    public AdminUser authenticateAdminUser(String username, String password) {
        AdminUser user = adminUserRepository.findByUsername(username);

        if (user == null) {
            throw new IllegalArgumentException("Admin user does not exist with username: " + username);
        }

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid password for admin user: " + username);
        }

        return user;
    }

}
