package com.darshan09200.employeemanagement;

public abstract class Vehicle {
    private VehicleMake make;
    private String plate;
    private VehicleColor color;
    private VehicleCategory category;

    public Vehicle(VehicleMake make, String plate, VehicleColor color, VehicleCategory category) {
        this.make = make;
        this.plate = plate;
        this.color = color;
        this.category = category;
    }

    public VehicleMake getMake() {
        return make;
    }

    public void setMake(VehicleMake make) {
        this.make = make;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public VehicleColor getColor() {
        return color;
    }

    public void setColor(VehicleColor color) {
        this.color = color;
    }

    public VehicleCategory getCategory() {
        return category;
    }

    public void setCategory(VehicleCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "\n\t- make: " + getMake() + "\n\t- plate: " + getPlate() + "\n\t- color: " + getColor() + "\n\t- category: " + getCategory() + "\n\t";
    }
}
