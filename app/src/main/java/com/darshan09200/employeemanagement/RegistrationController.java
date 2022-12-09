package com.darshan09200.employeemanagement;

import android.view.View;

import com.darshan09200.employeemanagement.databinding.ActivityRegistrationBinding;

public class RegistrationController {

    ActivityRegistrationBinding binding;

    public RegistrationController(ActivityRegistrationBinding binding) {
        this.binding = binding;

        resetUI();
    }

    public void resetUI() {
        binding.empId.setText("123");
        binding.dob.setText("12/34/5678");

        binding.bonusLabel.setText("Number of Clients");

        binding.vehicle.check(R.id.car);

        showVehicleType();
        hideSidecar();
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
}
