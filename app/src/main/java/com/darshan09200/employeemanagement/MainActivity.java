package com.darshan09200.employeemanagement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.darshan09200.employeemanagement.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<Employee> employees;
    ArrayAdapter<Employee> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        employees = new ArrayList<>();
        employees.addAll(Database.getInstance().getEmployees());
        System.out.println(employees);
        adapter = new ArrayAdapter<Employee>(this, android.R.layout.simple_list_item_2, android.R.id.text1, employees) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = view.findViewById(android.R.id.text1);
                TextView text2 = view.findViewById(android.R.id.text2);
                Employee employee = getItem(position);
                text1.setText(employee.getName());
                text2.setText(employee.getRole().getLabel());
                return view;
            }
        };
        adapter.setNotifyOnChange(true);
        binding.listView.setAdapter(adapter);
        binding.listView.setOnItemClickListener((parent, view, position, id) -> {
            System.out.println(parent.getAdapter() + " " + position);
        });

        binding.addEmployee.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchViewItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterItems(query, true);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterItems(newText, false);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        refreshData();
    }

    public void refreshData() {
        employees.clear();
        employees.addAll(Database.getInstance().getEmployees());
        adapter.notifyDataSetChanged();
    }

    public void filterItems(String input, boolean showToast) {
        String text = input.trim().toLowerCase();
        if (text.length() == 0) {
            refreshData();
            return;
        }
        ArrayList<Employee> allEmployeesData = Database.getInstance().getEmployees();
        ArrayList<Employee> filteredEmployees = new ArrayList<>();
        allEmployeesData.forEach(employee -> {
            if (employee.getName().toLowerCase().indexOf(text) > -1 || employee.getRole().getLabel().toLowerCase().indexOf(text) > -1)
                filteredEmployees.add(employee);
        });
        employees.clear();
        employees.addAll(filteredEmployees);
        adapter.notifyDataSetChanged();

        if (showToast && filteredEmployees.size() == 0) {
            Toast.makeText(MainActivity.this, "Not found", Toast.LENGTH_LONG).show();
        }
    }
}