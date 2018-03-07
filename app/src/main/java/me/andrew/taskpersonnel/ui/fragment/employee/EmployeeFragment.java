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

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import butterknife.BindView;
import me.andrew.taskpersonnel.App;
import me.andrew.taskpersonnel.R;
import me.andrew.taskpersonnel.data.Employee;
import me.andrew.taskpersonnel.presentation.presenter.employee.EmployeePresenter;
import me.andrew.taskpersonnel.presentation.view.employee.EmployeeView;
import me.andrew.taskpersonnel.ui.adapter.employee.EmployeeAdapter;
import me.andrew.taskpersonnel.ui.fragment.BaseFragment;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;
import static me.andrew.taskpersonnel.ui.fragment.specialty.SpecialtyFragment.SPECIALTY_NOT_DEFINED;

public class EmployeeFragment extends BaseFragment implements EmployeeView {

    public static final int EMPLOYEE_NOT_DEFINED = -1;
    private static final String TAG = "EmployeeFragment";
    private static final String ARG_SPECIALTY_ID = "specialty_ID";

    @InjectPresenter
    EmployeePresenter employeePresenter;

    @BindView(R.id.fragment_employee_recycler_view)
    RecyclerView employeeRecyclerView;

    private EmployeeAdapter employeeAdapter;

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

    @StringRes
    @Override
    public int changeActionBarTitle() {
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

        employeeAdapter = new EmployeeAdapter(employeePresenter);

        employeeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        employeeRecyclerView.setAdapter(employeeAdapter);

        employeeRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_IDLE)
                    return;

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                    employeePresenter.request();
                }
            }
        });

        employeeRecyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
                Log.d(TAG, "onChildViewAttachedToWindow, getSelectedPosition(): " + employeePresenter.getSelectedPosition()
                        + ", getChildAdapterPosition(): " + employeeRecyclerView.getChildAdapterPosition(view));

                view.setSelected(employeePresenter.getSelectedPosition() == employeeRecyclerView.getChildAdapterPosition(view));
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {

            }
        });
    }

    /**
     * Обновляет данные списка сотрудников, полученные из презентера
     */
    @Override
    public void updateItems(List<Employee> employees) {
        Log.d(TAG, "updateItems");
        // TODO: 02.03.2018 save first visible position and show it
        employeeAdapter.replaceList(employees);

        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE)
            employeePresenter.showDetailsFragment();
    }

    @Override
    public void onBackPressed() {
        employeePresenter.onBackCommandClick();
    }
}
