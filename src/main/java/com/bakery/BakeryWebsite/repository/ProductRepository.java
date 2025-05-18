package com.bakery.BakeryWebsite.repository;


import com.bakery.BakeryWebsite.models.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private final String filePath = "src/main/resources/products.txt";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                products.add(objectMapper.readValue(line, Product.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    public Optional<Product> findById(Long id) {
        return findAll().stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Product save(Product product) {
        List<Product> products = findAll();
        if (product.getId() == null) {
            product.setId(generateId(products));
        } else {
            products.removeIf(p -> p.getId().equals(product.getId()));
        }
        products.add(product);
        writeToFile(products);
        return product;
    }

    public void deleteById(Long id) {
        List<Product> products = findAll();
        products.removeIf(p -> p.getId().equals(id));
        writeToFile(products);
    }

    private Long generateId(List<Product> products) {
        return products.stream().map(Product::getId).max(Long::compare).orElse(0L) + 1;
    }

    private void writeToFile(List<Product> products) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Product product : products) {
                writer.write(objectMapper.writeValueAsString(product));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

