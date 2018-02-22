package com.example.andrew65appstask.ui.activity.splash;

import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.andrew65appstask.App;
import com.example.andrew65appstask.R;
import com.example.andrew65appstask.navigation.SplashActivityNavigator;
import com.example.andrew65appstask.presentation.presenter.splash.SplashActivityPresenter;
import com.example.andrew65appstask.presentation.view.splash.SplashActivityView;
import com.example.andrew65appstask.ui.activity.BaseActivity;

public class SplashActivity extends BaseActivity implements SplashActivityView {

    @SuppressWarnings("unused")
    @InjectPresenter
    SplashActivityPresenter splashActivityPresenter;

    @Override
    public void inject() {
        App.getSplashComponent().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.navigator = new SplashActivityNavigator(this, getSupportFragmentManager(), R.id.fragmentContainer);
    }
}