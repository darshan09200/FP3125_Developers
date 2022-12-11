package com.darshan09200.employeemanagement;

import android.app.DatePickerDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.darshan09200.employeemanagement.databinding.ActivityRegistrationBinding;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RegistrationController implements AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener,
        TextWatcher, DatePickerDialog.OnDateSetListener, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private static final String TAG = "RegistrationController";
    ActivityRegistrationBinding binding;
    Context context;

    DatePickerDialog datePickerDialog;

    ArrayList<String> employeeTypes;
    ArrayAdapter<String> employeeTypeAdapter;

    ArrayList<String> vehicleMakes;
    ArrayAdapter<String> vehicleMakeAdapter;

    ArrayList<String> vehicleCategories;
    ArrayAdapter<String> vehicleCategoryAdapter;

    ArrayList<String> vehicleTypes;
    ArrayAdapter<String> vehicleTypeAdapter;

    ArrayList<String> vehicleColours;
    ArrayAdapter<String> vehicleColourAdapter;

    public RegistrationController(Context context, ActivityRegistrationBinding binding) {
        this.binding = binding;
        this.context = context;

        Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(context, this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog
                .getDatePicker()
                .setMinDate(
                        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()
                );
        datePickerDialog
                .getDatePicker()
                .setMaxDate(
                        Date.from(LocalDate.now().minusYears(16).atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()
                );

        binding.firstName.addTextChangedListener(this);
        binding.lastName.addTextChangedListener(this);
        binding.dob.setOnClickListener(this);

        binding.monthlySalary.addTextChangedListener(this);
        binding.occupationRate.addTextChangedListener(this);

        binding.empType.setOnItemSelectedListener(this);
        employeeTypes = Registration.getInstance().getEmployeeTypeData();
        employeeTypeAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, employeeTypes);
        employeeTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.empType.setAdapter(employeeTypeAdapter);

        binding.bonus.addTextChangedListener(this);

        binding.vehicleKind.setOnCheckedChangeListener(this);

        vehicleMakes = Registration.getInstance().getVehicleMakeData();
        vehicleMakeAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, vehicleMakes);
        vehicleMakeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vehicleMakeAdapter.setNotifyOnChange(true);
        binding.vehicleMake.setAdapter(vehicleMakeAdapter);
        binding.vehicleMake.setOnItemSelectedListener(this);

        vehicleCategories = Registration.getInstance().getVehicleCategoryData();
        vehicleCategoryAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, vehicleCategories);
        vehicleCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vehicleCategoryAdapter.setNotifyOnChange(true);
        binding.vehicleCategory.setAdapter(vehicleCategoryAdapter);
        binding.vehicleCategory.setOnItemSelectedListener(this);

        vehicleTypes = Registration.getInstance().getVehicleTypeData();
        vehicleTypeAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, vehicleTypes);
        vehicleTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.vehicleType.setAdapter(vehicleTypeAdapter);
        binding.vehicleType.setOnItemSelectedListener(this);

        vehicleColours = Registration.getInstance().getVehicleColorData();
        vehicleColourAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, vehicleColours);
        vehicleColourAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.vehicleColor.setAdapter(vehicleColourAdapter);
        binding.vehicleColor.setOnItemSelectedListener(this);

        binding.sidecar.setOnCheckedChangeListener(this);
        binding.vehiclePlate.addTextChangedListener(this);

        binding.submit.setOnClickListener(this);
        resetUI();
    }

    public void resetUI() {
        binding.empId.setText("123");

        binding.firstName.setText(Registration.getInstance().getFirstName());
        binding.lastName.setText(Registration.getInstance().getLastName());
        binding.dob.setText(Registration.getInstance().getFormattedDate());
        binding.monthlySalary.setText(Registration.getInstance().getMonthlySalary());
        binding.occupationRate.setText(Registration.getInstance().getOccupationRate());
        binding.bonus.setText(Registration.getInstance().getBonusValue());
        binding.empType
                .setSelection(getSelectedIndex(employeeTypes, Registration.getInstance().getEmployeeType().getLabel()));
        int vehicleKindId = R.id.car;
        if (Registration.getInstance().getVehicleKind() == VehicleKind.MOTORCYCLE)
            vehicleKindId = R.id.motorcycle;
        binding.vehicleKind.check(vehicleKindId);

        binding.vehicleMake
                .setSelection(getSelectedIndex(vehicleMakes, Registration.getInstance().getVehicleMake().getLabel()));
        binding.vehicleType
                .setSelection(getSelectedIndex(vehicleTypes, Registration.getInstance().getVehicleType().getLabel()));
        binding.vehicleCategory.setSelection(
                getSelectedIndex(vehicleCategories, Registration.getInstance().getVehicleCategory().getLabel()));
        binding.vehicleColor.setSelection(
                getSelectedIndex(vehicleColours, Registration.getInstance().getVehicleColor().getLabel()));
        binding.sidecar.setChecked(Registration.getInstance().getSidecarChecked());
        binding.vehiclePlate.setText(Registration.getInstance().getVehiclePlate());
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

    private int getSelectedIndex(ArrayList<String> data, String selectedItem) {
        for (int i = 0; i < data.size(); i++) {
            String item = data.get(i);
            if (item.equalsIgnoreCase(selectedItem))
                return i;
        }
        return 0;
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

        binding.bonusLabel.setText(String.format("Number of %s", bonusText));
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

            case R.id.vehicleCategory:
                String vehicleCategoryValue = vehicleCategories.get(position);
                VehicleCategory vehicleCategory = Convertor.convertVehicleCategory(vehicleCategoryValue);
                Registration.getInstance().setVehicleCategory(vehicleCategory);
                break;

            case R.id.vehicleType:
                String vehicleTypeValue = vehicleTypes.get(position);
                VehicleType vehicleType = Convertor.convertVehicleType(vehicleTypeValue);
                Registration.getInstance().setVehicleType(vehicleType);
                break;

            case R.id.vehicleColor:
                String vehicleColorValue = vehicleColours.get(position);
                VehicleColor vehicleColor = Convertor.convertVehicleColor(vehicleColorValue);
                Registration.getInstance().setVehicleColor(vehicleColor);
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.motorcycle) {
            Registration.getInstance().setVehicleKind(VehicleKind.MOTORCYCLE);
            showSidecar();
            hideVehicleType();
        } else {
            Registration.getInstance().setVehicleKind(VehicleKind.CAR);
            showVehicleType();
            hideSidecar();
        }

        vehicleMakes.clear();
        vehicleMakes.addAll(Registration.getInstance().getVehicleMakeData());
        binding.vehicleMake.setSelection(0);

        vehicleCategories.clear();
        vehicleCategories.addAll(Registration.getInstance().getVehicleCategoryData());
        binding.vehicleCategory.setSelection(0);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Registration.getInstance().setDob(LocalDate.of(year, month + 1, dayOfMonth));
        binding.dob.setText(Registration.getInstance().getFormattedDate());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dob:
                if (!datePickerDialog.isShowing())
                    datePickerDialog.show();
                break;
            case R.id.submit:
                validate();
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (binding.firstName.isFocused())
            Registration.getInstance().setFirstName(s.toString());
        else if (binding.lastName.isFocused())
            Registration.getInstance().setLastName(s.toString());
        else if (binding.monthlySalary.isFocused())
            Registration.getInstance().setMonthlySalary(s.toString());
        else if (binding.occupationRate.isFocused())
            Registration.getInstance().setOccupationRate(s.toString());
        else if (binding.bonus.isFocused())
            Registration.getInstance().setBonusValue(s.toString());
        else if (binding.vehiclePlate.isFocused())
            Registration.getInstance().setVehiclePlate(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Registration.getInstance().setSidecarChecked(isChecked);
    }

    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void validate() {
        String firstName = Registration.getInstance().getFirstName();
        String lastName = Registration.getInstance().getLastName();
        LocalDate dob = Registration.getInstance().getDob();
        String monthlySalary = Registration.getInstance().getMonthlySalary();
        String occupationRate = Registration.getInstance().getOccupationRate();
        EmployeeType employeeType = Registration.getInstance().getEmployeeType();
        String bonusValue = Registration.getInstance().getBonusValue();
        VehicleKind vehicleKind = Registration.getInstance().getVehicleKind();
        VehicleMake vehicleMake = Registration.getInstance().getVehicleMake();
        VehicleCategory vehicleCategory = Registration.getInstance().getVehicleCategory();
        VehicleType vehicleType = Registration.getInstance().getVehicleType();
        VehicleColor vehicleColor = Registration.getInstance().getVehicleColor();
        Boolean isSidecarChecked = Registration.getInstance().getSidecarChecked();
        String vehiclePlate = Registration.getInstance().getVehiclePlate();

        String msg = "";
        if (firstName.isEmpty()) msg = "Please enter first name";
        else if (lastName.isEmpty()) msg = "Please enter last name";
        else if (monthlySalary.isEmpty()) msg = "Please enter monthly salary";
        else if (!isNumeric(monthlySalary)) msg = "Please enter valid monthly salary";
        else if (occupationRate.isEmpty()) msg = "Please enter occupation rate";
        else if (!isNumeric(occupationRate)) msg = "Please enter valid occupation rate";
        else if (bonusValue.isEmpty()) msg = "Please enter value";
        else if (!isNumeric(bonusValue)) msg = "Please enter valid value";
        else if (vehicleMake == VehicleMake.CHOOSE_MAKE) msg = VehicleMake.CHOOSE_MAKE.getLabel();
        else if (vehicleCategory == VehicleCategory.CHOSE_CATEGORY)
            msg = VehicleCategory.CHOSE_CATEGORY.getLabel();
        else if (vehicleKind == VehicleKind.CAR && vehicleType == VehicleType.CHOOSE_TYPE)
            msg = VehicleType.CHOOSE_TYPE.getLabel();
        else if (vehicleColor == VehicleColor.CHOOSE_COLOR)
            msg = VehicleColor.CHOOSE_COLOR.getLabel();
        else if (vehiclePlate.isEmpty()) msg = "Please enter vehicle plate";

        if (msg.length() > 0) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        } else {
            Employee employee;
            Vehicle vehicle;

            if (vehicleKind == VehicleKind.CAR) {
                vehicle = new Car(vehicleMake, vehiclePlate, vehicleColor, vehicleCategory, vehicleType);
            } else {
                vehicle = new Motorcycle(vehicleMake, vehiclePlate, vehicleColor, vehicleCategory, isSidecarChecked);
            }

            if (employeeType == EmployeeType.MANAGER) {
                employee = new Manager(firstName + " " + lastName, dob, Double.parseDouble(occupationRate), Double.parseDouble(monthlySalary), Integer.parseInt(bonusValue), vehicle);
            } else if (employeeType == EmployeeType.PROGRAMMER) {
                employee = new Programmer(firstName + " " + lastName, dob, Double.parseDouble(occupationRate), Double.parseDouble(monthlySalary), Integer.parseInt(bonusValue), vehicle);
            } else {
                employee = new Programmer(firstName + " " + lastName, dob, Double.parseDouble(occupationRate), Double.parseDouble(monthlySalary), Integer.parseInt(bonusValue), vehicle);
            }

            Log.d(TAG, "validate: " + employee);
        }
    }
}
