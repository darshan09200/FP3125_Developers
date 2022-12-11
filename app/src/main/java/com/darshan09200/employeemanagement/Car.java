package com.darshan09200.employeemanagement;

public class Car extends Vehicle {
    private String gear;
    private String type;

    public Car(String make, String plate, String color, String category, String gear, String type) {
        super(make, plate, color, category);
        this.gear = gear;
        this.type = type;
    }

    public String getGear() {
        return gear;
    }

    public void setGear(String gear) {
        this.gear = gear;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Employee has a car." + super.toString() + "- gear type: " + getGear() + "\n\t- type: " + getType();
    }
}
