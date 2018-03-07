package me.andrew.taskpersonnel.ui.adapter.employee;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.andrew.taskpersonnel.R;
import me.andrew.taskpersonnel.data.Employee;
import me.andrew.taskpersonnel.presentation.presenter.employee.EmployeePresenter;
import me.andrew.taskpersonnel.util.DateToStringFormatter;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeHolder> {

    private EmployeePresenter employeePresenter;

    private List<Employee> employees = new ArrayList<>();

    public EmployeeAdapter(EmployeePresenter employeePresenter) {
        this.employeePresenter = employeePresenter;
    }

    public void replaceList(List<Employee> employees) {
        this.employees = employees;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EmployeeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_employee, parent, false);
        return new EmployeeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeHolder holder, int position) {
        Employee employee = employees.get(position);

        holder.itemView.setSelected(employeePresenter.getSelectedPosition() == position);

        holder.bindEmployee(employee);
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    class EmployeeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private static final String TAG = "EmployeeHolder";

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
            int currentPosition = getAdapterPosition();
            int selectedPosition = employeePresenter.getSelectedPosition();

            Log.d(TAG, "EmployeeHolder.onClick currentPosition: " + currentPosition + ", selectedPosition: " + selectedPosition);

            if (currentPosition != selectedPosition) {

                if (selectedPosition != RecyclerView.NO_POSITION) {
                    RecyclerView recyclerView = (RecyclerView) v.getParent();

                    RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(selectedPosition);
                    if (viewHolder != null) {
                        CardView view = (CardView) viewHolder.itemView;
                        view.setSelected(false);
                    }
                }

                v.setSelected(true);
                selectedPosition = currentPosition;
            } else {
                if (v.getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE)
                    return;
            }

            employeePresenter.onClick(selectedPosition, employee.getId());
        }
    }
}
