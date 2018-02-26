package me.andrew.taskpersonnel.presentation.presenter.employee;

import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.InjectViewState;
import me.andrew.taskpersonnel.App;
import me.andrew.taskpersonnel.domain.GetEmployees;
import me.andrew.taskpersonnel.navigation.Screens;
import me.andrew.taskpersonnel.presentation.presenter.BasePresenter;
import me.andrew.taskpersonnel.presentation.view.employee.EmployeeView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static me.andrew.taskpersonnel.ui.fragment.employee.EmployeeFragment.EMPLOYEE_NOT_DEFINED;
import static me.andrew.taskpersonnel.ui.fragment.employee.EmployeeFragment.SPECIALTY_NOT_DEFINED;

@InjectViewState
public class EmployeePresenter extends BasePresenter<EmployeeView> {

    @Inject
    GetEmployees getEmployees;

    private int selectedPosition = RecyclerView.NO_POSITION;
    private int specialtyId = SPECIALTY_NOT_DEFINED;
    private int employeeId = EMPLOYEE_NOT_DEFINED;

    public void setSpecialtyId(int specialtyId) {
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

        setDisposable(getEmployees
                .executeUseCase(new GetEmployees.RequestValues(specialtyId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(employees -> getViewState().updateItems(employees)));
    }

    public void navigateToDetails() {
        if (employeeId != EMPLOYEE_NOT_DEFINED)
            router.navigateTo(Screens.DETAILS_FRAGMENT, employeeId);
    }

    public void onClick(int selectedPosition, int employeeId) {
        this.selectedPosition = selectedPosition;
        this.employeeId = employeeId;
        navigateToDetails();
//        router.navigateTo(Screens.DETAILS_FRAGMENT, employeeId);
    }
}
