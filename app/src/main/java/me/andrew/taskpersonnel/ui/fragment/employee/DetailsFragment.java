package me.andrew.taskpersonnel.ui.fragment.employee;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.andrew.taskpersonnel.App;
import me.andrew.taskpersonnel.R;
import me.andrew.taskpersonnel.data.AbstractSpecialty;
import me.andrew.taskpersonnel.data.Employee;
import me.andrew.taskpersonnel.data.Specialty;
import me.andrew.taskpersonnel.presentation.presenter.employee.DetailsPresenter;
import me.andrew.taskpersonnel.presentation.view.employee.DetailsView;
import me.andrew.taskpersonnel.ui.adapter.employee.SpecialtyAdapter;
import me.andrew.taskpersonnel.ui.fragment.BaseFragment;
import me.andrew.taskpersonnel.ui.view.employee.DoubleTextView;
import me.andrew.taskpersonnel.util.DateToStringFormatter;
import me.andrew.taskpersonnel.util.GlideLoggingListener;

import static me.andrew.taskpersonnel.ui.fragment.employee.EmployeeFragment.EMPLOYEE_NOT_DEFINED;

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

    private SpecialtyAdapter specialtyAdapter;

    public static Fragment newInstance(int employeeId) {
        Bundle args = new Bundle();
        args.putInt(ARG_EMPLOYEE_ID, employeeId);
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @ProvidePresenter
    DetailsPresenter provideDetailsPresenter() {
        final Bundle arguments = getArguments();
        int employeeId = EMPLOYEE_NOT_DEFINED;
        if (arguments != null) {
            employeeId = arguments.getInt(ARG_EMPLOYEE_ID);
        }
        return new DetailsPresenter(employeeId);
    }

    @Override
    public void inject() {
        App.getDetailsComponent().inject(this);
    }

    @StringRes
    @Override
    public int changeActionBarTitle() {
        return R.string.fragment_details_name;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_employee_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        specialtyAdapter = new SpecialtyAdapter();

        specialtiesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        specialtiesRecyclerView.setAdapter(specialtyAdapter);
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
        specialtyAdapter.replaceList(items);

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
}
