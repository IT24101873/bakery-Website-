package bakery.model;

public class InHouseBaker extends Baker {
    private String shift;

    public InHouseBaker(String id, String name, String specialization, String contactNumber, String availability, String shift) {
        super(id, name, specialization, contactNumber, availability);
        this.shift = shift;
    }

    public String getShift() { return shift; }
    public void setShift(String shift) { this.shift = shift; }

    @Override
    public String displayDetails() {
        return "In-House Baker: " + getName() + ", Shift: " + shift;
    }

    @Override
    public String toString() {
        return super.toString() + "," + shift;
    }
}