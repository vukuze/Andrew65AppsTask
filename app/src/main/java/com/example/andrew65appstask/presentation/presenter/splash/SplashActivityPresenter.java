package com.example.andrew65appstask.presentation.presenter.splash;

import com.example.andrew65appstask.App;
import com.example.andrew65appstask.navigation.Screens;
import com.example.andrew65appstask.presentation.presenter.BasePresenter;
import com.example.andrew65appstask.presentation.view.splash.SplashActivityView;

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