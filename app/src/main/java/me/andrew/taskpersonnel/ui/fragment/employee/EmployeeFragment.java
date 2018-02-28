package me.andrew.taskpersonnel.ui.fragment.employee;

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
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.andrew.taskpersonnel.App;
import me.andrew.taskpersonnel.R;
import me.andrew.taskpersonnel.data.Employee;
import me.andrew.taskpersonnel.presentation.presenter.employee.EmployeePresenter;
import me.andrew.taskpersonnel.presentation.view.employee.EmployeeView;
import me.andrew.taskpersonnel.ui.fragment.BaseFragment;
import me.andrew.taskpersonnel.util.DateToStringFormatter;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;
import static me.andrew.taskpersonnel.ui.fragment.specialty.SpecialtyFragment.SPECIALTY_NOT_DEFINED;

public class EmployeeFragment extends BaseFragment implements EmployeeView {

    private static final String TAG = "EmployeeFragment";
    private static final String ARG_SPECIALTY_ID = "specialty_ID";

    public static int EMPLOYEE_NOT_DEFINED = -1;

    @InjectPresenter
    EmployeePresenter employeePresenter;

    @BindView(R.id.fragment_employee_recycler_view)
    RecyclerView employeeRecyclerView;

    public static Fragment newInstance(int specialtyId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_SPECIALTY_ID, specialtyId);
        EmployeeFragment fragment = new EmployeeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @ProvidePresenter
    EmployeePresenter provideEmployeePresenter() {
        final Bundle arguments = getArguments();
        int specialtyId = SPECIALTY_NOT_DEFINED;
        if (arguments != null)
            specialtyId = arguments.getInt(ARG_SPECIALTY_ID);
        return new EmployeePresenter(specialtyId);
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
        return inflater.inflate(R.layout.fragment_employee, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        employeeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        employeeRecyclerView.setAdapter(new EmployeeAdapter(new ArrayList<>()));
    }

    /**
     * Обновляет данные списка сотрудников, полученные из презентера
     */
    @Override
    public void updateItems(List<Employee> employees) {
        Log.d(TAG, "updateItems");

        // TODO: 16.02.2018 не создавать каждый раз адаптер
        employeeRecyclerView.setAdapter(new EmployeeAdapter(employees));
        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE)
            employeePresenter.showDetailsFragment();
    }

    @Override
    public void onBackPressed() {
        employeePresenter.onBackCommandClick();
    }

    private void setCardViewBackgroundColor(CardView view, int color) {
        Activity activity = getActivity();
        if (activity != null) {
            view.setCardBackgroundColor(getResources().getColor(color));
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

        @NonNull
        @Override
        public EmployeeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.list_item_employee, parent, false);
            return new EmployeeHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull EmployeeHolder holder, int position) {
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
