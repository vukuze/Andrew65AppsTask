package com.example.andrew65appstask.presentation.presenter.main;

import com.example.andrew65appstask.App;
import com.example.andrew65appstask.navigation.Screens;
import com.example.andrew65appstask.presentation.presenter.BasePresenter;
import com.example.andrew65appstask.presentation.view.main.MainView;

public class MainPresenter extends BasePresenter<MainView> {

    @Override
    public void inject() {
        App.getSpecialtyComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        router.replaceScreen(Screens.SPLASH_FRAGMENT, null);
    }
}
