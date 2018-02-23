package com.andrew.personnel.ui.activity.splash;

import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.andrew.personnel.App;
import com.andrew.personnel.R;
import com.andrew.personnel.navigation.SplashActivityNavigator;
import com.andrew.personnel.presentation.presenter.splash.SplashActivityPresenter;
import com.andrew.personnel.presentation.view.splash.SplashActivityView;
import com.andrew.personnel.ui.activity.BaseActivity;

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