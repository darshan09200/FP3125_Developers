package com.darshan09200.employeemanagement;

public class Motorcycle extends Vehicle {
    private boolean sidecar;

    public Motorcycle(VehicleMake make, String plate, VehicleColor color, VehicleCategory category, boolean sidecar) {
        super(make, plate, color, category);
        this.sidecar = sidecar;
    }

    public boolean isSidecar() {
        return sidecar;
    }

    public void setSidecar(boolean sidecar) {
        this.sidecar = sidecar;
    }

    @Override
    public String toString() {
        return "Employee has a motorcycle." + super.toString() + "- " + (isSidecar() ? "with" : "without") + " sidecar";
    }
}
