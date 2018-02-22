package com.example.andrew65appstask.presentation.presenter.splash;

import com.arellomobile.mvp.InjectViewState;
import com.example.andrew65appstask.App;
import com.example.andrew65appstask.domain.UpdateData;
import com.example.andrew65appstask.navigation.Screens;
import com.example.andrew65appstask.presentation.presenter.BasePresenter;
import com.example.andrew65appstask.presentation.view.splash.SplashView;

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
}
