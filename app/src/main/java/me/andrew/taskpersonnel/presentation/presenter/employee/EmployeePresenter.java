package me.andrew.taskpersonnel.presentation.presenter.employee;

import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.andrew.taskpersonnel.App;
import me.andrew.taskpersonnel.data.Employee;
import me.andrew.taskpersonnel.domain.GetEmployees;
import me.andrew.taskpersonnel.navigation.Screens;
import me.andrew.taskpersonnel.presentation.presenter.BasePresenter;
import me.andrew.taskpersonnel.presentation.view.employee.EmployeeView;

import static me.andrew.taskpersonnel.ui.fragment.employee.EmployeeFragment.EMPLOYEE_NOT_DEFINED;

@InjectViewState
public class EmployeePresenter extends BasePresenter<EmployeeView> {

    @Inject
    GetEmployees getEmployees;

    private boolean reachedEndOfData = false;
    private int offset = 0;
    private int selectedPosition = RecyclerView.NO_POSITION;
    private int specialtyId;
    private int employeeId = EMPLOYEE_NOT_DEFINED;

    private List<Employee> employees = new ArrayList<>();

    public EmployeePresenter(int specialtyId) {
        this.specialtyId = specialtyId;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    @Override
    public void inject() {
        App.getEmployeeComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        request();
    }

    public void showDetailsFragment() {
        if (employeeId != EMPLOYEE_NOT_DEFINED)
            router.replaceScreen(Screens.DETAILS_FRAGMENT, employeeId);
    }

    public void request() {
        if (reachedEndOfData)
            return;

        final int LIMIT = 6;
        setDisposable(getEmployees
                .executeUseCase(new GetEmployees.RequestValues(specialtyId, LIMIT, offset))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(employeeList -> {
                    int size = employeeList.size();
                    if (size != 0) {
                        offset += size;
                        employees.addAll(employeeList);
                        getViewState().updateItems(employees);
                    } else {
                        reachedEndOfData = true;
                    }
                }));
    }

    public void onClick(int selectedPosition, int employeeId) {
        this.selectedPosition = selectedPosition;
        this.employeeId = employeeId;
        showDetailsFragment();
    }
}