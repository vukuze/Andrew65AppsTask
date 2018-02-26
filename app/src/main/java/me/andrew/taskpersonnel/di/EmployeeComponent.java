package me.andrew.taskpersonnel.di;

import me.andrew.taskpersonnel.di.scope.EmployeeScope;
import me.andrew.taskpersonnel.presentation.presenter.employee.EmployeeActivityPresenter;
import me.andrew.taskpersonnel.presentation.presenter.employee.EmployeePresenter;
import me.andrew.taskpersonnel.ui.activity.employee.EmployeeActivity;
import me.andrew.taskpersonnel.ui.fragment.employee.EmployeeFragment;

import dagger.Subcomponent;

@EmployeeScope
@Subcomponent
public interface EmployeeComponent {
    void inject(EmployeeActivity employeeActivity);

    void inject(EmployeeActivityPresenter employeeActivityPresenter);

    void inject(EmployeeFragment employeeFragment);

    void inject(EmployeePresenter employeePresenter);
}