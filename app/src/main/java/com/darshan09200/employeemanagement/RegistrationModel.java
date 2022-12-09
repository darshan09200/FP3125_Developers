package com.darshan09200.employeemanagement;


enum Vehicle {
    NONE("None"),
    CAR("Car"),
    MOTORCYCLE("Motorcycle");

    private String label;

    Vehicle(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    static Vehicle getVehicle(String vehicle) {
        switch (vehicle) {
            case "Car":
                return CAR;
            case "Motorcycle":
                return MOTORCYCLE;
        }
        return NONE;
    }
}

enum VehicleMake {
    CHOOSE_MAKE("Please choose a make", Vehicle.NONE),
    KAWASAKI("Kawasaki", Vehicle.MOTORCYCLE),
    HONDA("Honda", Vehicle.MOTORCYCLE),
    LAMBORGHINI("Lamborghini", Vehicle.CAR),
    BMW("BMW", Vehicle.CAR),
    RENAULT("Renault", Vehicle.CAR),
    MAZDA("Mazda", Vehicle.CAR);

    private final String label;
    private final Vehicle vehicle;

    VehicleMake(String label, Vehicle vehicle) {
        this.label = label;
        this.vehicle = vehicle;
    }


    public String getLabel() {
        return label;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    static VehicleMake getVehicleMake(String vehicleMake) {
        switch (vehicleMake) {
            case "Please choose a make":
                return CHOOSE_MAKE;
            case "Kawasaki":
                return KAWASAKI;
            case "Honda":
                return HONDA;
            case "Lamborghini":
                return LAMBORGHINI;
            case "BMW":
                return BMW;
            case "Renault":
                return RENAULT;
            case "Mazda":
                return MAZDA;
        }
        return null;
    }
}

enum VehicleCategory {
    CHOSE_CATEGORY("Please choose a category", Vehicle.NONE),
    RACE_MOTORCYCLE("Race Motorcycle", Vehicle.MOTORCYCLE),
    NOT_FOR_RACE("Not for Race", Vehicle.CAR),
    FAMILY("Family", Vehicle.CAR);
    private String label;
    private Vehicle vehicle;

    VehicleCategory(String label, Vehicle vehicle) {
        this.label = label;
        this.vehicle = vehicle;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String getLabel() {
        return label;
    }

}


public class RegistrationModel {
}
