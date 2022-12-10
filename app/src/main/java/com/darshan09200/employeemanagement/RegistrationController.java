package com.darshan09200.employeemanagement;

import android.app.DatePickerDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.RadioGroup;

import com.darshan09200.employeemanagement.databinding.ActivityRegistrationBinding;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

public class RegistrationController implements AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener,
		TextWatcher, DatePickerDialog.OnDateSetListener, View.OnClickListener {
	DatePickerDialog datePickerDialog;

	ActivityRegistrationBinding binding;

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

        binding.firstName.addTextChangedListener(this);

        Calendar calendar = Calendar.getInstance();
		datePickerDialog = new DatePickerDialog(context, this,
				calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH));

		binding.dob.setOnClickListener(this);

		employeeTypes = Registration.getInstance().getEmployeeTypeData();
		employeeTypeAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, employeeTypes);
		employeeTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		binding.empType.setAdapter(employeeTypeAdapter);

		vehicleMakes = Registration.getInstance().getVehicleMakeData();
		vehicleMakeAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, vehicleMakes);
		vehicleMakeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		vehicleMakeAdapter.setNotifyOnChange(true);
		binding.vehicleMake.setAdapter(vehicleMakeAdapter);

		vehicleCategories = Registration.getInstance().getVehicleCategoryData();
		vehicleCategoryAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, vehicleCategories);
		vehicleCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		vehicleCategoryAdapter.setNotifyOnChange(true);
		binding.vehicleCategory.setAdapter(vehicleCategoryAdapter);

		vehicleTypes = Registration.getInstance().getVehicleTypeData();
		vehicleTypeAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, vehicleTypes);
		vehicleTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		binding.vehicleType.setAdapter(vehicleTypeAdapter);

		vehicleColours = Registration.getInstance().getVehicleColorData();
		vehicleColourAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, vehicleColours);
		vehicleColourAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		binding.vehicleColor.setAdapter(vehicleColourAdapter);

		binding.vehicleKind.setOnCheckedChangeListener(this);

		binding.empType.setOnItemSelectedListener(this);

		binding.vehicleMake.setOnItemSelectedListener(this);
		binding.vehicleCategory.setOnItemSelectedListener(this);
		binding.vehicleType.setOnItemSelectedListener(this);
		binding.vehicleColor.setOnItemSelectedListener(this);

		resetUI();
	}

	public void resetUI() {
		binding.empId.setText("123");

		binding.firstName.setText(Registration.getInstance().getFirstName());
		binding.dob.setText("12/34/5678");

		onEmployeeTypeChanged();

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

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedString = Registration.getInstance().getDob().format(formatter);
		binding.dob.setText(formattedString);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.dob:
				if (!datePickerDialog.isShowing())
					datePickerDialog.show();
				break;
		}
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		Registration.getInstance().setFirstName(s.toString());
	}

	@Override
	public void afterTextChanged(Editable s) {

	}
}
