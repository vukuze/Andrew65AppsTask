package com.example.andrew65appstask.di;

import com.example.andrew65appstask.di.scope.EmployeeScope;
import com.example.andrew65appstask.presentation.presenter.employee.EmployeeActivityPresenter;
import com.example.andrew65appstask.presentation.presenter.employee.EmployeePresenter;
import com.example.andrew65appstask.ui.activity.employee.EmployeeActivity;
import com.example.andrew65appstask.ui.fragment.employee.EmployeeFragment;

import dagger.Subcomponent;

@EmployeeScope
@Subcomponent
public interface EmployeeComponent {
    void inject(EmployeeActivity employeeActivity);

    void inject(EmployeeActivityPresenter employeeActivityPresenter);

    void inject(EmployeeFragment employeeFragment);

    void inject(EmployeePresenter employeePresenter);
}