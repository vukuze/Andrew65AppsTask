package me.andrew.taskpersonnel.ui.activity.splash;

import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;

import me.andrew.taskpersonnel.App;
import me.andrew.taskpersonnel.R;
import me.andrew.taskpersonnel.navigation.SplashActivityNavigator;
import me.andrew.taskpersonnel.presentation.presenter.splash.SplashActivityPresenter;
import me.andrew.taskpersonnel.presentation.view.splash.SplashActivityView;
import me.andrew.taskpersonnel.ui.activity.BaseActivity;

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