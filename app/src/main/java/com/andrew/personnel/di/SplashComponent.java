package com.andrew.personnel.di;

import com.andrew.personnel.di.scope.SplashScope;
import com.andrew.personnel.presentation.presenter.splash.SplashActivityPresenter;
import com.andrew.personnel.presentation.presenter.splash.SplashPresenter;
import com.andrew.personnel.ui.activity.splash.SplashActivity;
import com.andrew.personnel.ui.fragment.splash.SplashFragment;

import dagger.Subcomponent;

@SplashScope
@Subcomponent
public interface SplashComponent {
    void inject(SplashActivity splashActivity);

    void inject(SplashActivityPresenter splashActivityPresenter);

    void inject(SplashFragment splashFragment);

    void inject(SplashPresenter splashPresenter);
}
