package com.example.andrew65appstask.ui.fragment.employee;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.andrew65appstask.App;
import com.example.andrew65appstask.R;
import com.example.andrew65appstask.data.Employee;
import com.example.andrew65appstask.presentation.presenter.employee.EmployeePresenter;
import com.example.andrew65appstask.presentation.view.employee.EmployeeView;
import com.example.andrew65appstask.ui.fragment.BaseFragment;
import com.example.andrew65appstask.util.DateToStringFormatter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmployeeFragment extends BaseFragment implements EmployeeView {

    private static final String TAG = "EmployeeFragment";
    private static final String ARG_EMPLOYEE_REQUEST_DATA = "employee_request_data";
    public static int SPECIALTY_NOT_DEFINED = -1;
    public static int EMPLOYEE_NOT_DEFINED = -1;

    @InjectPresenter
    EmployeePresenter employeePresenter;

    @BindView(R.id.fragment_employee_recycler_view)
    RecyclerView employeeRecyclerView;

    public static Fragment newInstance(RequestData employeeRequestData) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_EMPLOYEE_REQUEST_DATA, employeeRequestData);
        EmployeeFragment fragment = new EmployeeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void inject() {
        App.getEmployeeComponent().inject(this);
    }

    @Override
    public int setActionBarTitle() {
        return R.string.fragment_employee_name;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");

        return inflater.inflate(R.layout.fragment_employee, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);

        employeeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        employeeRecyclerView.setAdapter(new EmployeeAdapter(new ArrayList<>()));

        final Bundle arguments = getArguments();

        RequestData employeeRequestData = null;
        if (arguments != null)
            employeeRequestData = (RequestData) arguments.getSerializable(ARG_EMPLOYEE_REQUEST_DATA);
        if (employeeRequestData != null)
            employeePresenter.setSpecialtyId(employeeRequestData.getSpecialtyId());
    }

    /**
     * Обновляет данные списка сотрудников, полученные из презентера
     */
    @Override
    public void updateItems(List<Employee> employees) {
        Log.d(TAG, "updateItems");

        // TODO: 16.02.2018 не создавать каждый раз адаптер
        employeeRecyclerView.setAdapter(new EmployeeAdapter(employees));
        employeePresenter.navigateToDetails();
    }

    @Override
    public void onBackPressed() {
        employeePresenter.onBackCommandClick();
    }

    private void setCardViewBackgroundColor(CardView view, int color) {
        Activity activity = getActivity();
        if (activity != null) {
            boolean isLandscape = activity.findViewById(R.id.detail_fragment_container) != null;
            if (isLandscape) {
                view.setCardBackgroundColor(getResources().getColor(color));
            }
        }
    }

    public static class RequestData implements Serializable {
        private int specialtyId = SPECIALTY_NOT_DEFINED;
        private int employeeId = EMPLOYEE_NOT_DEFINED;

        int getSpecialtyId() {
            return specialtyId;
        }

        public void setSpecialtyId(int specialtyId) {
            this.specialtyId = specialtyId;
        }

        public int getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(int employeeId) {
            this.employeeId = employeeId;
        }
    }

    class EmployeeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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
                    CardView view = (CardView) employeeRecyclerView.findViewHolderForAdapterPosition(selectedPosition).itemView;
                    setCardViewBackgroundColor(view, R.color.colorDefaultBackground);
                }

                setCardViewBackgroundColor((CardView) v, R.color.colorSelectedBackground);
                selectedPosition = currentPosition;
            }

            employeePresenter.onClick(selectedPosition, employee.getId());
        }
    }

    private class EmployeeAdapter extends RecyclerView.Adapter<EmployeeHolder> {

        private List<Employee> employees;

        EmployeeAdapter(List<Employee> employees) {
            this.employees = employees;
        }

        @Override
        public EmployeeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.list_item_employee, parent, false);
            return new EmployeeHolder(view);
        }

        @Override
        public void onBindViewHolder(EmployeeHolder holder, int position) {
            Employee employee = employees.get(position);

            CardView cardView = (CardView) holder.itemView;

            setCardViewBackgroundColor(cardView,
                    employeePresenter.getSelectedPosition() == position ? R.color.colorSelectedBackground : R.color.colorDefaultBackground);
//            holder.itemView.setSelected(selectedPosition == position);

            holder.bindEmployee(employee);
        }

        @Override
        public int getItemCount() {
            return employees.size();
        }
    }
}
