package com.darshan09200.employeemanagement;

import java.util.ArrayList;

public class Database {
    private static Database instance;

    private final ArrayList<Employee> employees;

    private Database() {
        employees = new ArrayList<>();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        Registration.getInstance().resetFields();
    }

    public String getNewEmpId() {
        return String.format("EMP-%03d", (employees.size() + 1));
    }
}
