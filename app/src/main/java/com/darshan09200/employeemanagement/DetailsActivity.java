package com.darshan09200.employeemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.darshan09200.employeemanagement.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {

    ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Employee employee = Database.getInstance().getEmployee(getIntent().getStringExtra("empId"));

        if (employee != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(employee.getEmpId());
            binding.empId.setText(employee.getEmpId());
            binding.name.setText(employee.getName());
            binding.age.setText(String.valueOf(employee.getAge()));
            binding.role.setText(employee.getRole().getLabel());
            double annualIncome = 0;
            String bonusLabel = "";
            int bonusValue = 0;
            if (employee instanceof Manager) {
                Manager manager = (Manager) employee;
                annualIncome = manager.getAnnualIncome();
                bonusLabel = "Clients";
                bonusValue = manager.getNbClients();
            } else if (employee instanceof Programmer) {
                Programmer programmer = (Programmer) employee;
                annualIncome = programmer.getAnnualIncome();
                bonusLabel = "Projects";
                bonusValue = programmer.getNbProjects();
            } else if (employee instanceof Tester) {
                Tester tester = (Tester) employee;
                annualIncome = tester.getAnnualIncome();
                bonusLabel = "Bugs";
                bonusValue = tester.getNbBugs();
            }
            binding.annualIncome.setText(String.format("$ %.2f", annualIncome));
            binding.bonusLabel.setText(String.format("Number of %s", bonusLabel));
            binding.bonus.setText(String.format("%d", bonusValue));

            Vehicle vehicle = employee.getVehicle();

            binding.vehicleMake.setText(vehicle.getMake().getLabel());
            binding.vehicleCategory.setText(vehicle.getCategory().getLabel());
            binding.vehicleColor.setText(vehicle.getColor().getLabel());
            binding.vehiclePlate.setText(vehicle.getPlate());

            if (vehicle instanceof Car) {
                Car car = (Car) vehicle;
                binding.vehicleKind.setText(VehicleKind.CAR.getLabel());
                binding.vehicleExtrasLabel.setText("Vehicle Type");
                binding.vehicleExtras.setText(car.getType().getLabel());
            } else if (vehicle instanceof Motorcycle) {
                Motorcycle motorcycle = (Motorcycle) vehicle;
                binding.vehicleKind.setText(VehicleKind.MOTORCYCLE.getLabel());
                binding.vehicleExtrasLabel.setText("Sidecar");
                binding.vehicleExtras.setText(motorcycle.isSidecar() ? "Yes" : "No");
            }
        } else {
            finish();
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }
}