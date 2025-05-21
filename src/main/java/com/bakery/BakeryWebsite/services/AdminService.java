package com.bakery.BakeryWebsite.services;

import com.bakery.BakeryWebsite.models.Admin;
import com.bakery.BakeryWebsite.repository.AdminRepository;
import java.util.List;

public class AdminService {
    private AdminRepository adminRepository;

    public AdminService() {
        this.adminRepository = new AdminRepository();
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getAdminById(Long id) {
        Admin admin = adminRepository.findById(id);
        if (admin == null) {
            throw new RuntimeException("Admin not found with id: " + id);
        }
        return admin;
    }

    public Admin createAdmin(Admin admin) {
        if (admin.getId() == null || admin.getUsername() == null || admin.getEmail() == null) {
            throw new RuntimeException("All fields are required");
        }
        adminRepository.save(admin);
        return admin;
    }

    public Admin updateAdmin(Long id, Admin adminDetails) {
        Admin existingAdmin = adminRepository.findById(id);
        if (existingAdmin == null) {
            throw new RuntimeException("Admin not found with id: " + id);
        }
        existingAdmin.setUsername(adminDetails.getUsername());
        existingAdmin.setEmail(adminDetails.getEmail());
        adminRepository.save(existingAdmin);
        return existingAdmin;
    }

    public void deleteAdmin(Long id) {
        if (adminRepository.findById(id) == null) {
            throw new RuntimeException("Admin not found with id: " + id);
        }
        adminRepository.delete(id);
    }
}