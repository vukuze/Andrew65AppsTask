package com.andrew.personnel.presentation.presenter.employee;

import com.arellomobile.mvp.InjectViewState;
import com.andrew.personnel.App;
import com.andrew.personnel.domain.GetEmployeeDetails;
import com.andrew.personnel.presentation.presenter.BasePresenter;
import com.andrew.personnel.presentation.view.employee.DetailsView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.andrew.personnel.ui.fragment.employee.EmployeeFragment.EMPLOYEE_NOT_DEFINED;

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