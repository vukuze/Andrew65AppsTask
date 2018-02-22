package com.example.andrew65appstask.presentation.presenter.employee;

import com.arellomobile.mvp.InjectViewState;
import com.example.andrew65appstask.App;
import com.example.andrew65appstask.domain.GetEmployeeDetails;
import com.example.andrew65appstask.presentation.presenter.BasePresenter;
import com.example.andrew65appstask.presentation.view.employee.DetailsView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.example.andrew65appstask.ui.fragment.employee.EmployeeFragment.EMPLOYEE_NOT_DEFINED;
import static com.example.andrew65appstask.ui.fragment.employee.EmployeeFragment.SPECIALTY_NOT_DEFINED;

@InjectViewState
public class DetailsPresenter extends BasePresenter<DetailsView> {

    @Inject
    GetEmployeeDetails getEmployeeDetails;

    private int employeeId = EMPLOYEE_NOT_DEFINED;

    @Override
    public void inject() {
        App.getDetailsComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        setDisposable(getEmployeeDetails.executeUseCase(new GetEmployeeDetails.RequestValues(employeeId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(employee -> getViewState().updateItems(employee)));
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}