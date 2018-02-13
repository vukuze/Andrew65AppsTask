package com.example.andrew65appstask.presentation.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.example.andrew65appstask.App;
import com.example.andrew65appstask.presentation.view.MainView;

@InjectViewState
public class MainPresenter extends BasePresenter<MainView> {

    @Override
    public void inject() {
        App.getSpecialtyComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        Log.d(this.getClass().getSimpleName(), "onFirstViewAttach");
        getViewState().showSplashFragment();
    }
}
