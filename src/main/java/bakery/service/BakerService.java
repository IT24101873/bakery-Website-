package bakery.service;

import bakery.model.Baker;
import bakery.model.FreelanceBaker;
import bakery.model.InHouseBaker;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BakerService {

    private static final String BAKERS_FILE = "src/main/resources/data/bakers.txt";

    public List<Baker> findAll() throws IOException {
        List<Baker> bakers = new ArrayList<>();
        File file = new File(BAKERS_FILE);
        if (!file.exists()) {
            return bakers;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    String id = parts[0];
                    String name = parts[1];
                    String specialization = parts[2];
                    String contactNumber = parts[3];
                    String availability = parts[4];

                    if (parts.length == 6 && id.startsWith("FL")) {
                        double hourlyRate = Double.parseDouble(parts[5]);
                        bakers.add(new FreelanceBaker(id, name, specialization, contactNumber, availability, hourlyRate));
                    } else if (parts.length == 6 && id.startsWith("IH")) {
                        String shift = parts[5];
                        bakers.add(new InHouseBaker(id, name, specialization, contactNumber, availability, shift));
                    } else {
                        bakers.add(new Baker(id, name, specialization, contactNumber, availability));
                    }
                }
            }
        }
        return bakers;
    }

    public Baker findById(String id) throws IOException {
        return findAll().stream()
                .filter(baker -> baker.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void save(Baker baker) throws IOException {
        List<Baker> bakers = findAll();
        if (baker.getId() == null || baker.getId().isEmpty()) {
            String prefix = baker instanceof FreelanceBaker ? "FL" : baker instanceof InHouseBaker ? "IH" : "BKR";
            baker.setId(prefix + UUID.randomUUID().toString().substring(0, 3));
        }
        bakers.add(baker);
        writeToFile(bakers);
    }

    public void update(Baker updatedBaker) throws IOException {
        List<Baker> bakers = findAll();
        bakers.removeIf(baker -> baker.getId().equals(updatedBaker.getId()));
        bakers.add(updatedBaker);
        writeToFile(bakers);
    }

    public void delete(String id) throws IOException {
        List<Baker> bakers = findAll();
        bakers.removeIf(baker -> baker.getId().equals(id));
        writeToFile(bakers);
    }

    private void writeToFile(List<Baker> bakers) throws IOException {
        File file = new File(BAKERS_FILE);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Baker baker : bakers) {
                writer.write(baker.toString());
                writer.newLine();
            }
        }
    }
}