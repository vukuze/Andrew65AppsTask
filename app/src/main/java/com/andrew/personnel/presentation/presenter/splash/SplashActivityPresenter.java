package com.andrew.personnel.presentation.presenter.splash;

import com.andrew.personnel.App;
import com.andrew.personnel.navigation.Screens;
import com.andrew.personnel.presentation.presenter.BasePresenter;
import com.andrew.personnel.presentation.view.splash.SplashActivityView;

public class SplashActivityPresenter extends BasePresenter<SplashActivityView> {

    @Override
    public void inject() {
        App.getSplashComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        router.replaceScreen(Screens.SPLASH_FRAGMENT, null);
    }
}