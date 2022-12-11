package com.darshan09200.employeemanagement;

public abstract class Vehicle {
    private String make;
    private String plate;
    private String color;
    private String category;

    public Vehicle(String make, String plate, String color, String category) {
        this.make = make;
        this.plate = plate;
        this.color = color;
        this.category = category;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    @Override
    public String toString() {
        return "\n\t- make: " + getMake() + "\n\t- plate: " + getPlate() + "\n\t- color: " + getColor() + "\n\t- category: " + getCategory() + "\n\t";
    }
}
