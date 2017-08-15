package com.example.andrew65appstask.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.example.andrew65appstask.App;
import com.example.andrew65appstask.domain.GetEmployees;
import com.example.andrew65appstask.presentation.view.EmployeeView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class EmployeePresenter extends BasePresenter<EmployeeView> {

    @Inject
    GetEmployees getEmployees;

    @Override
    public void inject() {
        App.getEmployeeComponent().inject(this);
    }

    public void request(int specialtyId) {
        setDisposable(getEmployees.executeUseCase(new GetEmployees.RequestValues(specialtyId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(employees -> getViewState().updateItems(employees)));
    }

    @Override
    public void onBackCommandClick() {
        super.onBackCommandClick();
        router.exit();
    }
}
