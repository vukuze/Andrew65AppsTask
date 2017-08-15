package com.example.andrew65appstask.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.example.andrew65appstask.App;
import com.example.andrew65appstask.domain.UpdateData;
import com.example.andrew65appstask.navigation.Screens;
import com.example.andrew65appstask.presentation.view.SplashView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class SplashPresenter extends BasePresenter<SplashView> {

    @Inject
    UpdateData networkData;

    @Override
    public void inject() {
        App.getSpecialtyComponent().inject(this);
    }

    public void request() {
        setDisposable(networkData
                .executeUseCase(new UpdateData.RequestValues())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        employees -> router.replaceScreen(Screens.SPECIALTY_FRAGMENT),
                        throwable -> getViewState().handleErrors(throwable)));
    }

    @Override
    public void onBackCommandClick() {
        super.onBackCommandClick();
        router.exit();
    }
}
