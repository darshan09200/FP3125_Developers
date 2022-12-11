package com.darshan09200.employeemanagement;

import java.time.LocalDate;

public class Manager extends Employee {
    static final double GAIN_FACTOR_CLIENT = 500;
    private int nbClients;

    public Manager(String name, LocalDate dob, double occupationRate, int nbClients, Vehicle vehicle) {
        super(name, dob, occupationRate, "Manager", vehicle);
        this.nbClients = nbClients;
    }

    public int getNbClients() {
        return nbClients;
    }

    public void setNbClients(int nbClients) {
        this.nbClients = nbClients;
    }

    @Override
    public double getAnnualIncome() {
        return super.getAnnualIncome() + (nbClients * GAIN_FACTOR_CLIENT);
    }

    @Override
    public String toString() {
        String desc = super.toString();
        desc += ". He/She has bought " + nbClients + " new clients.\nHis/Her estimated annual income is $" + getAnnualIncome();
        return desc;
    }

}
