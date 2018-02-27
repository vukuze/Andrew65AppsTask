package me.andrew.taskpersonnel.presentation.presenter.employee;

import me.andrew.taskpersonnel.App;
import me.andrew.taskpersonnel.navigation.Screens;
import me.andrew.taskpersonnel.presentation.presenter.BasePresenter;
import me.andrew.taskpersonnel.presentation.view.employee.EmployeeActivityView;

public class EmployeeActivityPresenter extends BasePresenter<EmployeeActivityView> {

    private int specialtyId;

    public EmployeeActivityPresenter(int specialtyId) {
        super();
        this.specialtyId = specialtyId;
    }

    @Override
    public void inject() {
        App.getEmployeeComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        router.replaceScreen(Screens.EMPLOYEE_FRAGMENT, specialtyId);
    }
}