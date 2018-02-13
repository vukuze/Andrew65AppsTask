package com.example.andrew65appstask.ui.adapter.employee;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.andrew65appstask.R;
import com.example.andrew65appstask.data.Employee;
import com.example.andrew65appstask.ui.activity.MainActivity;

import java.util.List;

import javax.inject.Inject;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeHolder> {

    @Inject
    MainActivity activity;

    private List<Employee> employees;

    public EmployeeAdapter(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public EmployeeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View view = inflater.inflate(R.layout.list_item_employee, parent, false);
        return new EmployeeHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeeHolder holder, int position) {
        Employee employee = employees.get(position);
        holder.bindEmployee(employee);
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }
}
