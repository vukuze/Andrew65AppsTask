package me.andrew.taskpersonnel.presentation.presenter.splash;

import me.andrew.taskpersonnel.App;
import me.andrew.taskpersonnel.navigation.Screens;
import me.andrew.taskpersonnel.presentation.presenter.BasePresenter;
import me.andrew.taskpersonnel.presentation.view.splash.SplashActivityView;

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