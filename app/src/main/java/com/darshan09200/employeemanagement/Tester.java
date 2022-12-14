package com.darshan09200.employeemanagement;

import java.time.LocalDate;

public class Tester extends Employee {
    static final double GAIN_FACTOR_ERROR = 10;
    private int nbBugs;

    public Tester(String empId, String name, LocalDate dob, double occupationRate, double monthlySalary, int nbBugs, Vehicle vehicle) {
        super(empId, name, dob, occupationRate, monthlySalary, EmployeeType.TESTER, vehicle);
        this.nbBugs = nbBugs;
    }

    public int getNbBugs() {
        return nbBugs;
    }

    public void setNbBugs(int nbBugs) {
        this.nbBugs = nbBugs;
    }

    public String toString() {
        String desc = super.toString();
        desc += " and corrected " + getNbBugs() + " bugs.\nHis/Her estimated annual income is $" + getAnnualIncome();
        return desc;
    }

    public double getAnnualIncome() {
        return super.getAnnualIncome() + (nbBugs * GAIN_FACTOR_ERROR);
    }

}
