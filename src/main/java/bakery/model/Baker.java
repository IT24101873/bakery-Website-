package bakery.model;

public class Baker {
    private String id;
    private String name;
    private String specialization;
    private String contactNumber;
    private String availability;

    public Baker() {}

    public Baker(String id, String name, String specialization, String contactNumber, String availability) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.contactNumber = contactNumber;
        this.availability = availability;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public String getAvailability() { return availability; }
    public void setAvailability(String availability) { this.availability = availability; }

    public String displayDetails() {
        return "Baker: " + name + ", Specialization: " + specialization;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + specialization + "," + contactNumber + "," + availability;
    }
}