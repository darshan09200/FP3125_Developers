package com.darshan09200.employeemanagement;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.darshan09200.employeemanagement.databinding.ActivityRegistrationBinding;

import java.util.ArrayList;

public class RegistrationController implements AdapterView.OnItemSelectedListener {

    ActivityRegistrationBinding binding;

    ArrayList<String> employeeTypes;
    ArrayAdapter<String> employeeTypeAdapter;

    ArrayList<String> vehicleMakes;
    ArrayAdapter<String> vehicleMakeAdapter;

    ArrayList<String> vehicleCategory;
    ArrayAdapter<String> vehicleCategoryAdapter;

    ArrayList<String> vehicleType;
    ArrayAdapter<String> vehicleTypeAdapter;

    ArrayList<String> vehicleColour;
    ArrayAdapter<String> vehicleColourAdapter;

    public RegistrationController(Context context, ActivityRegistrationBinding binding) {
        this.binding = binding;

        employeeTypes = Registration.getInstance().getEmployeeTypeData();
        employeeTypeAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, employeeTypes);
        employeeTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.empType.setAdapter(employeeTypeAdapter);

        vehicleMakes = Registration.getInstance().getVehicleMakeData();
        vehicleMakeAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, vehicleMakes);
        vehicleMakeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.vehicleMake.setAdapter(vehicleMakeAdapter);

        vehicleCategory = Registration.getInstance().getVehicleCategoryData();
        vehicleCategoryAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, vehicleCategory);
        vehicleCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.vehicleCategory.setAdapter(vehicleCategoryAdapter);

        vehicleType = Registration.getInstance().getVehicleTypeData();
        vehicleTypeAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, vehicleType);
        vehicleTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.vehicleType.setAdapter(vehicleTypeAdapter);

        vehicleColour = Registration.getInstance().getVehicleColorData();
        vehicleColourAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, vehicleColour);
        vehicleColourAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.vehicleColor.setAdapter(vehicleColourAdapter);

        binding.vehicleKind.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.motorcycle) {
                showSidecar();
                hideVehicleType();
            } else {
                showVehicleType();
                hideSidecar();
            }
        });

        binding.empType.setOnItemSelectedListener(this);

        binding.vehicleMake.setOnItemSelectedListener(this);
        resetUI();
    }


    public void resetUI() {
        binding.empId.setText("123");
        binding.dob.setText("12/34/5678");

        onEmployeeTypeChanged();
        binding.vehicleKind.check(R.id.car);
    }

    private void showVehicleType() {
        if (binding.vehicleTypeLabel.getVisibility() != View.VISIBLE) {
            binding.vehicleTypeLabel.setVisibility(View.VISIBLE);
            binding.vehicleType.setVisibility(View.VISIBLE);
        }
    }

    private void hideVehicleType() {
        if (binding.vehicleTypeLabel.getVisibility() != View.GONE) {
            binding.vehicleTypeLabel.setVisibility(View.GONE);
            binding.vehicleType.setVisibility(View.GONE);
        }
    }

    private void showSidecar() {
        if (binding.sidecarLayout.getVisibility() != View.VISIBLE) {
            binding.sidecarLayout.setVisibility(View.VISIBLE);
        }
    }

    private void hideSidecar() {
        if (binding.sidecarLayout.getVisibility() != View.GONE) {
            binding.sidecarLayout.setVisibility(View.GONE);
        }
    }

    private void onEmployeeTypeChanged() {
        EmployeeType employeeType = Registration.getInstance().getEmployeeType();
        String bonusText = "";
        if (employeeType == EmployeeType.MANAGER) {
            bonusText = "Clients";
        } else if (employeeType == EmployeeType.TESTER) {
            bonusText = "Bugs";
        } else if (employeeType == EmployeeType.PROGRAMMER) {
            bonusText = "Projects";
        }

        binding.bonusLabel.setText("Number of " + bonusText);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.empType:
                String employeeTypeValue = employeeTypes.get(position);
                EmployeeType employeeType = Convertor.convertEmployeeType(employeeTypeValue);
                Registration.getInstance().setEmployeeType(employeeType);
                onEmployeeTypeChanged();
                break;

            case R.id.vehicleMake:
                String vehicleMakeValue = vehicleMakes.get(position);
                VehicleMake vehicleMake = Convertor.convertVehicleMake(vehicleMakeValue);
                Registration.getInstance().setVehicleMake(vehicleMake);
                break;

                
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
