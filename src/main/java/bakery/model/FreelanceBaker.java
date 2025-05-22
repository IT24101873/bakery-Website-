package bakery.model;

public class FreelanceBaker extends Baker {
    private double hourlyRate;

    public FreelanceBaker(String id, String name, String specialization, String contactNumber, String availability, double hourlyRate) {
        super(id, name, specialization, contactNumber, availability);
        this.hourlyRate = hourlyRate;
    }

    public double getHourlyRate() { return hourlyRate; }
    public void setHourlyRate(double hourlyRate) { this.hourlyRate = hourlyRate; }

    @Override
    public String displayDetails() {
        return "Freelance Baker: " + getName() + ", Rate: $" + hourlyRate;
    }

    @Override
    public String toString() {
        return super.toString() + "," + hourlyRate;
    }
}