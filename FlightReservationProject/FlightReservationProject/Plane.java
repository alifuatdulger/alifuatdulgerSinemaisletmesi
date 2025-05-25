public class Plane {
    private String model;
    private String brand;
    private String serialNo;
    private int capacity;
    private int yearBuilt;

    public Plane() {}

    public Plane(String model, String brand, String serialNo, int capacity, int yearBuilt) {
        this.model = model;
        this.brand = brand;
        this.serialNo = serialNo;
        this.capacity = capacity;
        this.yearBuilt = yearBuilt;
    }

    public String getModel() { return model; }
    public String getBrand() { return brand; }
    public String getSerialNo() { return serialNo; }
    public int getCapacity() { return capacity; }
    public int getYearBuilt() { return yearBuilt; }

    public String toString() {
        return brand + " " + model + " (" + serialNo + ") - Capacity: " + capacity;
    }
}
