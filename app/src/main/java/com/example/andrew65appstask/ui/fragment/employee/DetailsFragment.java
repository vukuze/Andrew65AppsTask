package com.example.andrew65appstask.ui.fragment.employee;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.example.andrew65appstask.App;
import com.example.andrew65appstask.R;
import com.example.andrew65appstask.data.AbstractSpecialty;
import com.example.andrew65appstask.data.Employee;
import com.example.andrew65appstask.data.Specialty;
import com.example.andrew65appstask.presentation.presenter.employee.DetailsPresenter;
import com.example.andrew65appstask.presentation.view.employee.DetailsView;
import com.example.andrew65appstask.ui.adapter.specialty.BaseSpecialtyAdapter;
import com.example.andrew65appstask.ui.fragment.BaseFragment;
import com.example.andrew65appstask.ui.view.employee.DoubleTextView;
import com.example.andrew65appstask.ui.view.specialty.BaseSpecialtyHolder;
import com.example.andrew65appstask.util.DateToStringFormatter;
import com.example.andrew65appstask.util.GlideLoggingListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DetailsFragment extends BaseFragment implements DetailsView {

    private static final String TAG = "DetailsFragment";
    private static final String ARG_EMPLOYEE_ID = "employee_id";

    @InjectPresenter
    DetailsPresenter detailsPresenter;

    @BindView(R.id.employee_fname)
    DoubleTextView firstNameView;

    @BindView(R.id.employee_lname)
    DoubleTextView lastNameView;

    @BindView(R.id.employee_birthday)
    DoubleTextView birthdayView;

    @BindView(R.id.employee_age)
    DoubleTextView ageView;

    @BindView(R.id.employee_avatr)
    ImageView avatarImageView;

    @BindView(R.id.fragment_specialty_recycler_view)
    RecyclerView specialtiesRecyclerView;

    public static Fragment newInstance(int employeeId) {
        Bundle args = new Bundle();
        args.putInt(ARG_EMPLOYEE_ID, employeeId);
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void inject() {
        App.getDetailsComponent().inject(this);
    }

    @Override
    public int setActionBarTitle() {
        return R.string.fragment_details_name;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        return inflater.inflate(R.layout.fragment_employee_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);

        specialtiesRecyclerView.setAdapter(new SpecialtyAdapter(new ArrayList<>()));
        specialtiesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        final Bundle arguments = getArguments();

        int employeeId = 0;
        if (arguments != null) {
            employeeId = arguments.getInt(ARG_EMPLOYEE_ID);
        }

        detailsPresenter.setEmployeeId(employeeId);
    }

    /**
     * Обновляет представления на основе данных из презентера
     */
    public void updateItems(Employee employee) {
        Log.d(TAG, "updateItems");

        firstNameView.setText(employee.getFName());
        lastNameView.setText(employee.getLName());
        birthdayView.setText(DateToStringFormatter.getBirthday(employee.getBirthday()));
        ageView.setText(DateToStringFormatter.getAge(employee.getBirthday()));

        List<Specialty> items = new ArrayList<>();
        for (AbstractSpecialty abstractSpecialty : employee.getSpecialties()) {
            items.add((Specialty) abstractSpecialty);
        }
        specialtiesRecyclerView.setAdapter(new SpecialtyAdapter(items));

        Glide.with(this)
                .load(employee.getAvatrUrl())
                .listener(new GlideLoggingListener<>())
                .placeholder(R.drawable.ic_loading_image)
                .error(R.drawable.ic_cancel_loading)
                .into(avatarImageView);
    }

    @Override
    public void onBackPressed() {
        detailsPresenter.onBackCommandClick();
    }

    private class SpecialtyAdapter extends BaseSpecialtyAdapter<BaseSpecialtyHolder> {

        SpecialtyAdapter(List<Specialty> specialties) {
            super(specialties);
        }

        @Override
        public BaseSpecialtyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new BaseSpecialtyHolder(inflate(parent, viewType));
        }
    }
}
