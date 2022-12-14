package com.darshan09200.employeemanagement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

enum EmployeeType {
    MANAGER("Manager"),
    PROGRAMMER("Programmer"),
    TESTER("Tester");

    private final String label;

    EmployeeType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

enum VehicleKind {
    BOTH("Both"),
    CAR("Car"),
    MOTORCYCLE("Motorcycle");

    private final String label;

    VehicleKind(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

enum VehicleMake {
    CHOOSE_MAKE("Please choose a make", VehicleKind.BOTH),
    KAWASAKI("Kawasaki", VehicleKind.MOTORCYCLE),
    HONDA("Honda", VehicleKind.BOTH),
    LAMBORGHINI("Lamborghini", VehicleKind.CAR),
    BMW("BMW", VehicleKind.CAR),
    RENAULT("Renault", VehicleKind.CAR),
    MAZDA("Mazda", VehicleKind.CAR);

    private final String label;
    private final VehicleKind vehicleKind;

    VehicleMake(String label, VehicleKind vehicleKind) {
        this.label = label;
        this.vehicleKind = vehicleKind;
    }

    public String getLabel() {
        return label;
    }

    public VehicleKind getVehicle() {
        return vehicleKind;
    }
}

enum VehicleCategory {
    CHOSE_CATEGORY("Please choose a category", VehicleKind.BOTH),
    RACE_MOTORCYCLE("Race Motorcycle", VehicleKind.MOTORCYCLE),
    NOT_FOR_RACE("Not for Race", VehicleKind.BOTH),
    FAMILY("Family", VehicleKind.CAR);

    private final String label;
    private final VehicleKind vehicleKind;

    VehicleCategory(String label, VehicleKind vehicleKind) {
        this.label = label;
        this.vehicleKind = vehicleKind;
    }

    public String getLabel() {
        return label;
    }

    public VehicleKind getVehicle() {
        return vehicleKind;
    }
}

enum VehicleType {
    CHOOSE_TYPE("Please choose a type"),
    SEDAN("Sedan"),
    SPORT("Sport"),
    HATCHBACK("Hatchback"),
    SUV("SUV");

    private final String label;

    VehicleType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

enum VehicleColor {
    CHOOSE_COLOR("Please choose a color"),
    YELLOW("Yellow"),
    BLACK("Black"),
    WHITE("White"),
    RED("Red");

    private final String label;

    VehicleColor(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

public class Registration {
    private static Registration instance;

    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String monthlySalary;
    private String occupationRate;
    private EmployeeType employeeType;
    private String bonusValue;
    private VehicleKind vehicleKind;
    private VehicleMake vehicleMake;
    private VehicleCategory vehicleCategory;
    private VehicleType vehicleType;
    private VehicleColor vehicleColor;
    private Boolean isSidecarChecked;
    private String vehiclePlate;

    private Registration() {
        resetFields();
    }

    public static Registration getInstance() {
        if (instance == null)
            instance = new Registration();
        return instance;
    }

    public void resetFields(){
        firstName = "";
        lastName = "";
        dob = LocalDate.now().minusYears(16);
        monthlySalary = "";
        occupationRate = "";
        employeeType = EmployeeType.MANAGER;
        bonusValue = "";
        vehicleKind = VehicleKind.CAR;
        vehicleMake = VehicleMake.CHOOSE_MAKE;
        vehicleCategory = VehicleCategory.CHOSE_CATEGORY;
        vehicleType = VehicleType.CHOOSE_TYPE;
        vehicleColor = VehicleColor.CHOOSE_COLOR;
        isSidecarChecked = false;
        vehiclePlate = "";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedString = dob.format(formatter);
        return formattedString;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(String monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public String getOccupationRate() {
        return occupationRate;
    }

    public void setOccupationRate(String occupationRate) {
        this.occupationRate = occupationRate;
    }

    public String getBonusValue() {
        return bonusValue;
    }

    public void setBonusValue(String bonusValue) {
        this.bonusValue = bonusValue;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public VehicleKind getVehicleKind() {
        return vehicleKind;
    }

    public void setVehicleKind(VehicleKind vehicleKind) {
        this.vehicleKind = vehicleKind;
    }

    public VehicleMake getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(VehicleMake vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public VehicleCategory getVehicleCategory() {
        return vehicleCategory;
    }

    public void setVehicleCategory(VehicleCategory vehicleCategory) {
        this.vehicleCategory = vehicleCategory;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public VehicleColor getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(VehicleColor vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }

    public Boolean getSidecarChecked() {
        return isSidecarChecked;
    }

    public void setSidecarChecked(Boolean sidecarChecked) {
        isSidecarChecked = sidecarChecked;
    }

    public ArrayList<String> getEmployeeTypeData() {
        ArrayList<String> employeeTypes = new ArrayList<>();

        for (EmployeeType employeeType : EmployeeType.values()) {
            employeeTypes.add(employeeType.getLabel());
        }
        return employeeTypes;
    }

    public ArrayList<String> getVehicleMakeData() {
        ArrayList<String> vehicleMakes = new ArrayList<>();

        for (VehicleMake vehicleMake : VehicleMake.values()) {
            if (vehicleMake.getVehicle() == vehicleKind || vehicleMake.getVehicle() == VehicleKind.BOTH)
                vehicleMakes.add(vehicleMake.getLabel());
        }
        return vehicleMakes;
    }

    public ArrayList<String> getVehicleCategoryData() {
        ArrayList<String> vehicleCategories = new ArrayList<>();

        for (VehicleCategory vehicleCategory : VehicleCategory.values()) {
            if (vehicleCategory.getVehicle() == vehicleKind || vehicleCategory.getVehicle() == VehicleKind.BOTH)
                vehicleCategories.add(vehicleCategory.getLabel());
        }
        return vehicleCategories;
    }

    public ArrayList<String> getVehicleTypeData() {
        ArrayList<String> vehicleTypes = new ArrayList<>();

        for (VehicleType vehicleType : VehicleType.values()) {
            vehicleTypes.add(vehicleType.getLabel());
        }
        return vehicleTypes;
    }

    public ArrayList<String> getVehicleColorData() {
        ArrayList<String> vehicleColors = new ArrayList<>();

        for (VehicleColor vehicleColor : VehicleColor.values()) {
            vehicleColors.add(vehicleColor.getLabel());
        }
        return vehicleColors;
    }

}
