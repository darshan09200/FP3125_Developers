package com.darshan09200.employeemanagement;

public class Car extends Vehicle {
    private VehicleType type;

    public Car(VehicleMake make, String plate, VehicleColor color, VehicleCategory category, VehicleType type) {
        super(make, plate, color, category);
        this.type = type;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Employee has a car." + super.toString() + "- type: " + getType();
    }
}
