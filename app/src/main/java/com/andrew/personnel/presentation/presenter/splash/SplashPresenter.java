package com.andrew.personnel.presentation.presenter.splash;

import com.arellomobile.mvp.InjectViewState;
import com.andrew.personnel.App;
import com.andrew.personnel.domain.UpdateData;
import com.andrew.personnel.navigation.Screens;
import com.andrew.personnel.presentation.presenter.BasePresenter;
import com.andrew.personnel.presentation.view.splash.SplashView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class SplashPresenter extends BasePresenter<SplashView> {

    @Inject
    UpdateData networkData;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        request();
    }

    @Override
    public void inject() {
        App.getSplashComponent().inject(this);
    }

    public void request() {
        setDisposable(networkData
                .executeUseCase(new UpdateData.RequestValues())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        employees -> router.newRootScreen(Screens.SPECIALTY_ACTIVITY),
                        throwable -> getViewState().handleErrors(throwable)));
    }
}
