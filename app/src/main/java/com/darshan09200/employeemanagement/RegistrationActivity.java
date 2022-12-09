package com.darshan09200.employeemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.darshan09200.employeemanagement.databinding.ActivityRegistrationBinding;

public class RegistrationActivity extends AppCompatActivity {

    ActivityRegistrationBinding binding;

    RegistrationController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        controller = new RegistrationController(binding);
        setContentView(binding.getRoot());
    }
}