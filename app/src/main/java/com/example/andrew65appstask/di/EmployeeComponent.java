package com.example.andrew65appstask.di;

import com.example.andrew65appstask.di.scope.EmployeeScope;
import com.example.andrew65appstask.presentation.presenter.EmployeePresenter;
import com.example.andrew65appstask.ui.fragment.EmployeeFragment;

import dagger.Subcomponent;

@EmployeeScope
@Subcomponent
public interface EmployeeComponent {
    void inject(EmployeeFragment employeeFragment);

    void inject(EmployeePresenter employeePresenter);
}