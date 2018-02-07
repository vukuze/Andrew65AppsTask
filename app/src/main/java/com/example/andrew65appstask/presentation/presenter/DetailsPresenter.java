package com.example.andrew65appstask.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.example.andrew65appstask.App;
import com.example.andrew65appstask.domain.GetEmployeeDetails;
import com.example.andrew65appstask.presentation.view.DetailsView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class DetailsPresenter extends BasePresenter<DetailsView> {

    @Inject
    GetEmployeeDetails getEmployeeDetails;
    private int employeeId;

    @Override
    public void inject() {
        App.getDetailsComponent().inject(this);
    }

    public void request(int employeeId) {
        this.employeeId = employeeId;
        setDisposable(getEmployeeDetails.executeUseCase(new GetEmployeeDetails.RequestValues(employeeId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(employee -> getViewState().updateItems(employee)));
    }
}