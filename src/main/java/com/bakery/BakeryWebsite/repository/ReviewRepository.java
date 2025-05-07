package com.bakery.BakeryWebsite.repository;


import com.bakery.BakeryWebsite.models.Review;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository {
    private static final String FILE_PATH = "review.txt";

    public List<Review> findAll() {
        List<Review> reviews = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    Review review = new Review();
                    review.setId(parts[0]);
                    review.setCustomerName(parts[1]);
                    review.setCreatedAt(LocalDateTime.parse(parts[2]));
                    review.setComment(parts[3]);
                    review.setRating(Integer.parseInt(parts[4]));
                    reviews.add(review);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    public Review findById(String id) {
        return findAll().stream()
                .filter(review -> review.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Review save(Review review) {
        List<Review> reviews = findAll();
        reviews.removeIf(r -> r.getId().equals(review.getId()));
        reviews.add(review);
        writeToFile(reviews);
        return review;
    }

    public void delete(String id) {
        List<Review> reviews = findAll();
        reviews.removeIf(review -> review.getId().equals(id));
        writeToFile(reviews);
    }

    private void writeToFile(List<Review> reviews) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Review review : reviews) {
                writer.write(review.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


