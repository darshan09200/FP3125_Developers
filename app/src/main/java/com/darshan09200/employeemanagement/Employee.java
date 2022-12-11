package com.darshan09200.employeemanagement;

import java.time.LocalDate;
import java.time.Period;

public abstract class Employee {
    static int EMP_COUNT = 0;
    static final double DEFAULT_OCCUPATION_RATE = 100;
    public final String empId;
    private final String name;
    private LocalDate dob;
    private double occupationRate;
    private double monthlySalary;
    private EmployeeType role;
    private Vehicle vehicle;

    public Employee(String name, LocalDate dob, double occupationRate, double monthlySalary, EmployeeType role, Vehicle vehicle) {
        this.empId = String.format("EMP-%03d", ++EMP_COUNT);
        this.name = name;
        this.dob = dob;
        this.occupationRate = formatOccupationRate(occupationRate);
        this.occupationRate = monthlySalary;
        this.role = role;
        this.vehicle = vehicle;

        System.out.println("We have a new employee: " + getName() + ", a " + getRole() + ".");
    }

    public String getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    private static double formatOccupationRate(double occupationRate) {
        return Math.max(0, Math.min(occupationRate, DEFAULT_OCCUPATION_RATE));
    }

    public double getOccupationRate() {
        return occupationRate;
    }

    public void setOccupationRate(double occupationRate) {
        this.occupationRate = formatOccupationRate(occupationRate);
    }

    public EmployeeType getRole() {
        return role;
    }

    public void setRole(EmployeeType role) {
        this.role = role;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public double getAnnualIncome() {
        return getMonthlySalary() * 12 * (getOccupationRate() / 100);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        String desc = "Name:" + getName() + ",a " + getRole() + "\n" + "Age: " + getAge() + "\n";
        if (vehicle != null) {
            desc += vehicle + "\n";
        }
        return desc + getName() + " has an Occupation rate: " + getOccupationRate() + "%";
    }
}
