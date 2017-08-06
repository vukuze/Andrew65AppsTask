package com.example.andrew65appstask.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.example.andrew65appstask.navigation.Screens;
import com.example.andrew65appstask.util.DateToStringFormatter;
import com.example.andrew65appstask.presentation.presenter.EmployeePresenter;
import com.example.andrew65appstask.presentation.view.EmployeeView;
import com.example.andrew65appstask.ui.BackButtonListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.commands.Back;
import ru.terrakok.cicerone.commands.Forward;

public class EmployeeFragment extends BaseFragmentWithNavigator implements EmployeeView, BackButtonListener {

    private static final String TAG = "EmployeeFragment";
    private static final String ARG_SPECIALTY_ID = "specialty_id";

    @InjectPresenter
    EmployeePresenter employeePresenter;

    @BindView(R.id.fragment_employee_recycler_view)
    RecyclerView employeeRecyclerView;

    private Callbacks callbacks;

    public static Fragment newInstance(int specialtyId) {
        Bundle args = new Bundle();
        args.putInt(ARG_SPECIALTY_ID, specialtyId);
        EmployeeFragment fragment = new EmployeeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Navigator createNavigator() {
        return command -> {
            if (command instanceof Forward) {
                Forward cmd = (Forward) command;
                if (cmd.getScreenKey().equals(Screens.DETAILS_ACTIVITY)) {
                    callbacks.onEmployeeSelected((int) cmd.getTransitionData());
                }
            } else if (command instanceof Back) {
                Log.d(TAG, "Back");
                getActivity().finish();
            }
        };
    }

    @Override
    public void inject() {
        App.getEmployeeComponent().inject(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callbacks = (Callbacks) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");

        View view = inflater.inflate(R.layout.fragment_employee, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);

        employeeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        employeeRecyclerView.setAdapter(new EmployeeAdapter(new ArrayList<>()));

        int specialtyId = getArguments().getInt(ARG_SPECIALTY_ID);
        employeePresenter.request(specialtyId);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = null;
    }

    /**
     * Обновляет данные списка сотрудников, полученные из презентера
     */
    @Override
    public void updateItems(List<Employee> employees) {
        Log.d(TAG, "updateItems");

        employeeRecyclerView.setAdapter(new EmployeeAdapter(employees));
    }

    @Override
    public boolean onBackPressed() {
        Log.d(TAG, "onBackPressed");
        employeePresenter.onBackCommandClick();
        return true;
    }

    /**
     * Интерфейс для передачи данных активности-хосту
     */
    public interface Callbacks {
        void onEmployeeSelected(int employeeId);
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
            navigator.applyCommand(new Forward(Screens.DETAILS_ACTIVITY, employee.getId()));
        }
    }

    private class EmployeeAdapter extends RecyclerView.Adapter<EmployeeHolder> {

        private List<Employee> employees;

        public EmployeeAdapter(List<Employee> employees) {
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
            holder.bindEmployee(employee);
        }

        @Override
        public int getItemCount() {
            return employees.size();
        }
    }
}
