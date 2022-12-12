package com.darshan09200.employeemanagement;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

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

    public Employee getEmployee(String empId) {
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            if (employee.getEmpId().equals(empId)) return employee;
        }
        return null;
    }

    public String getNewEmpId() {
        return String.format("EMP-%03d", (employees.size() + 1));
    }
}
