package bakery.controller;

import bakery.model.Baker;
import bakery.model.FreelanceBaker;
import bakery.model.InHouseBaker;
import bakery.service.BakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class BakerController {

    @Autowired
    private BakerService bakerService;

    @GetMapping("/bakers")
    public String showBakerList(Model model) {
        try {
            model.addAttribute("bakers", bakerService.findAll());
            return "bakerList";
        } catch (IOException e) {
            model.addAttribute("error", "Failed to load bakers: " + e.getMessage());
            return "bakerList";
        }
    }

    @GetMapping("/bakers/add")
    public String showAddBakerForm(Model model) {
        model.addAttribute("baker", new Baker());
        model.addAttribute("isFreelance", false);
        model.addAttribute("isInHouse", false);
        return "addBaker";
    }

    @PostMapping("/bakers")
    public String addBaker(@ModelAttribute Baker baker, @RequestParam String bakerType,
                           @RequestParam(required = false) Double hourlyRate,
                           @RequestParam(required = false) String shift, Model model) {
        if (baker.getName() == null || baker.getName().trim().isEmpty()) {
            model.addAttribute("error", "Name is required.");
            return "addBaker";
        }
        if (baker.getSpecialization() == null || baker.getSpecialization().trim().isEmpty()) {
            model.addAttribute("error", "Specialization is required.");
            return "addBaker";
        }
        if (baker.getContactNumber() == null || !baker.getContactNumber().matches("\\d{10}")) {
            model.addAttribute("error", "Contact number must be a 10-digit number.");
            return "addBaker";
        }

        try {
            Baker newBaker;
            if ("freelance".equals(bakerType)) {
                newBaker = new FreelanceBaker(null, baker.getName(), baker.getSpecialization(),
                        baker.getContactNumber(), baker.getAvailability(), hourlyRate != null ? hourlyRate : 0.0);
            } else if ("inhouse".equals(bakerType)) {
                newBaker = new InHouseBaker(null, baker.getName(), baker.getSpecialization(),
                        baker.getContactNumber(), baker.getAvailability(), shift != null ? shift : "");
            } else {
                newBaker = baker;
            }
            bakerService.save(newBaker);
            model.addAttribute("message", "Baker added successfully!");
            return "redirect:/bakers";
        } catch (IOException e) {
            model.addAttribute("error", "Failed to add baker: " + e.getMessage());
            return "addBaker";
        }
    }

    @GetMapping("/bakers/edit")
    public String showEditBakerForm(@RequestParam String id, Model model) {
        try {
            Baker baker = bakerService.findById(id);
            if (baker != null) {
                model.addAttribute("baker", baker);
                model.addAttribute("isFreelance", baker instanceof FreelanceBaker);
                model.addAttribute("isInHouse", baker instanceof InHouseBaker);
                return "editBaker";
            }
            return "redirect:/bakers";
        } catch (IOException e) {
            model.addAttribute("error", "Failed to load baker: " + e.getMessage());
            return "redirect:/bakers";
        }
    }

    @PostMapping("/bakers/update")
    public String updateBaker(@ModelAttribute Baker baker, @RequestParam String bakerType,
                              @RequestParam(required = false) Double hourlyRate,
                              @RequestParam(required = false) String shift, Model model) {
        if (baker.getName() == null || baker.getName().trim().isEmpty()) {
            model.addAttribute("error", "Name is required.");
            return "editBaker";
        }
        if (baker.getSpecialization() == null || baker.getSpecialization().trim().isEmpty()) {
            model.addAttribute("error", "Specialization is required.");
            return "editBaker";
        }
        if (baker.getContactNumber() == null || !baker.getContactNumber().matches("\\d{10}")) {
            model.addAttribute("error", "Contact number must be a 10-digit number.");
            return "editBaker";
        }

        try {
            Baker updatedBaker;
            if ("freelance".equals(bakerType)) {
                updatedBaker = new FreelanceBaker(baker.getId(), baker.getName(), baker.getSpecialization(),
                        baker.getContactNumber(), baker.getAvailability(), hourlyRate != null ? hourlyRate : 0.0);
            } else if ("inhouse".equals(bakerType)) {
                updatedBaker = new InHouseBaker(baker.getId(), baker.getName(), baker.getSpecialization(),
                        baker.getContactNumber(), baker.getAvailability(), shift != null ? shift : "");
            } else {
                updatedBaker = baker;
            }
            bakerService.update(updatedBaker);
            model.addAttribute("message", "Baker updated successfully!");
            return "redirect:/bakers";
        } catch (IOException e) {
            model.addAttribute("error", "Failed to update baker: " + e.getMessage());
            return "editBaker";
        }
    }

    @GetMapping("/bakers/delete")
    public String deleteBaker(@RequestParam String id, Model model) {
        try {
            bakerService.delete(id);
            model.addAttribute("message", "Baker deleted successfully!");
            return "redirect:/bakers";
        } catch (IOException e) {
            model.addAttribute("error", "Failed to delete baker: " + e.getMessage());
            return "redirect:/bakers";
        }
    }
}