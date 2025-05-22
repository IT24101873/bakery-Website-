package com.bakery.BakeryWebsite.controllers;
// AdminUserController.java

import com.bakery.BakeryWebsite.models.AdminUser;
import com.bakery.BakeryWebsite.services.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminUserController {

    private final AdminUserService adminUserService;

    @Autowired
    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @GetMapping
    public List<AdminUser> getAllAdminUsers() {
        return adminUserService.getAllAdminUsers();
    }

    @GetMapping("/{userId}")
    public AdminUser getAdminUserById(@PathVariable long userId) {
        return adminUserService.getAdminUserById(userId);
    }

    @PostMapping
    public AdminUser saveAdminUser(@RequestBody AdminUser user) {
        return adminUserService.createAdminUser(user.getUsername(), user.getPassword(), user.getAdminLevel());
    }

    @PutMapping("/{userId}")
    public AdminUser updateAdminUser(@RequestBody AdminUser user, @PathVariable long userId) {
        return adminUserService.updateAdminUser(userId, user.getUsername(), user.getPassword(), user.getAdminLevel(), user.getEmail());
    }

    @DeleteMapping("/{userId}")
    public String deleteAdminUser(@PathVariable long userId) {
        adminUserService.deleteAdminUser(userId);
        return "Admin user with ID " + userId + " deleted successfully";
    }

    @PostMapping("/login")
    public AdminUser loginAdminUser(@RequestBody AdminUser user) {
        AdminUser authenticatedUser = adminUserService.authenticateAdminUser(user.getUsername(), user.getPassword());
        if (authenticatedUser == null) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        return authenticatedUser;
    }


}

