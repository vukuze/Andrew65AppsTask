package me.andrew.taskpersonnel.di;

import me.andrew.taskpersonnel.di.scope.SplashScope;
import me.andrew.taskpersonnel.presentation.presenter.splash.SplashActivityPresenter;
import me.andrew.taskpersonnel.presentation.presenter.splash.SplashPresenter;
import me.andrew.taskpersonnel.ui.activity.splash.SplashActivity;
import me.andrew.taskpersonnel.ui.fragment.splash.SplashFragment;

import dagger.Subcomponent;

@SplashScope
@Subcomponent
public interface SplashComponent {
    void inject(SplashActivity splashActivity);

    void inject(SplashActivityPresenter splashActivityPresenter);

    void inject(SplashFragment splashFragment);

    void inject(SplashPresenter splashPresenter);
}
