package com.example.andrew65appstask.ui.adapter.employee;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.andrew65appstask.R;
import com.example.andrew65appstask.data.Employee;
import com.example.andrew65appstask.util.DateToStringFormatter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmployeeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.item_employee_lname_text_view)
    TextView lNameTextView;

    @BindView(R.id.item_employee_fname_text_view)
    TextView fNameTextView;

    @BindView(R.id.item_employee_age_text_view)
    TextView ageTextView;

    private Employee employee;

    EmployeeHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    void bindEmployee(Employee employee) {
        this.employee = employee;
        lNameTextView.setText(this.employee.getLName());
        fNameTextView.setText(this.employee.getFName());
        ageTextView.setText(DateToStringFormatter.getAge(this.employee.getBirthday()));
    }

    @Override
    public void onClick(View v) {
//            navigator.applyCommand(new Forward(Screens.DETAILS_ACTIVITY, employee.getId()));
        //navigator.applyCommand(new Forward(Screens.DETAILS_FRAGMENT, employee.getId()));
    }
}