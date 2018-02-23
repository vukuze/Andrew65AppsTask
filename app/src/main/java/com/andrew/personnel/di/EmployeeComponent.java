package com.andrew.personnel.di;

import com.andrew.personnel.di.scope.EmployeeScope;
import com.andrew.personnel.presentation.presenter.employee.EmployeeActivityPresenter;
import com.andrew.personnel.presentation.presenter.employee.EmployeePresenter;
import com.andrew.personnel.ui.activity.employee.EmployeeActivity;
import com.andrew.personnel.ui.fragment.employee.EmployeeFragment;

import dagger.Subcomponent;

@EmployeeScope
@Subcomponent
public interface EmployeeComponent {
    void inject(EmployeeActivity employeeActivity);

    void inject(EmployeeActivityPresenter employeeActivityPresenter);

    void inject(EmployeeFragment employeeFragment);

    void inject(EmployeePresenter employeePresenter);
}