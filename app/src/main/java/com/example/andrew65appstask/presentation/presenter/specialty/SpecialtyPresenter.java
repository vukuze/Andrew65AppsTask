package com.example.andrew65appstask.presentation.presenter.specialty;

import com.arellomobile.mvp.InjectViewState;
import com.example.andrew65appstask.App;
import com.example.andrew65appstask.domain.GetSpecialties;
import com.example.andrew65appstask.navigation.Screens;
import com.example.andrew65appstask.presentation.presenter.BasePresenter;
import com.example.andrew65appstask.presentation.view.specialty.SpecialtyView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class SpecialtyPresenter extends BasePresenter<SpecialtyView> {

    @Inject
    GetSpecialties getSpecialties;

    @Override
    public void inject() {
        App.getSpecialtyComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        // TODO: 16.02.2018 в случае ошибки тут что-то не должно работать
        setDisposable(getSpecialties
                .executeUseCase(new GetSpecialties.RequestValues())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(specialties -> getViewState().updateItems(specialties)));
    }

    public void onClick(int specialtyId) {
        router.navigateTo(Screens.EMPLOYEE_ACTIVITY, specialtyId);
    }
}