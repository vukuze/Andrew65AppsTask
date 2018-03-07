package me.andrew.taskpersonnel.ui.activity.splash;

import com.arellomobile.mvp.presenter.InjectPresenter;

import me.andrew.taskpersonnel.App;
import me.andrew.taskpersonnel.R;
import me.andrew.taskpersonnel.navigation.SplashActivityNavigator;
import me.andrew.taskpersonnel.presentation.presenter.splash.SplashActivityPresenter;
import me.andrew.taskpersonnel.presentation.view.splash.SplashActivityView;
import me.andrew.taskpersonnel.ui.activity.BaseActivity;
import ru.terrakok.cicerone.Navigator;

public class SplashActivity extends BaseActivity implements SplashActivityView {

    @InjectPresenter
    SplashActivityPresenter splashActivityPresenter;

    @Override
    public void inject() {
        App.getSplashComponent().inject(this);
    }

    @Override
    public Navigator createNavigator() {
        return new SplashActivityNavigator(this, getSupportFragmentManager(), R.id.fragmentContainer);
    }
}