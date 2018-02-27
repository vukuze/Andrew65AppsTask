package me.andrew.taskpersonnel.presentation.presenter.employee;

import me.andrew.taskpersonnel.App;
import me.andrew.taskpersonnel.navigation.Screens;
import me.andrew.taskpersonnel.presentation.presenter.BasePresenter;
import me.andrew.taskpersonnel.presentation.view.employee.DetailsActivityView;

public class DetailsActivityPresenter extends BasePresenter<DetailsActivityView> {

    private int employeeId;

    public DetailsActivityPresenter(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public void inject() {
        App.getDetailsComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        router.replaceScreen(Screens.DETAILS_FRAGMENT, employeeId);
    }
}