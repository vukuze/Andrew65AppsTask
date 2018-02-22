package com.example.andrew65appstask.di;

import com.example.andrew65appstask.di.scope.SplashScope;
import com.example.andrew65appstask.presentation.presenter.splash.SplashActivityPresenter;
import com.example.andrew65appstask.presentation.presenter.splash.SplashPresenter;
import com.example.andrew65appstask.ui.activity.splash.SplashActivity;
import com.example.andrew65appstask.ui.fragment.splash.SplashFragment;

import dagger.Subcomponent;

@SplashScope
@Subcomponent
public interface SplashComponent {
    void inject(SplashActivity splashActivity);

    void inject(SplashActivityPresenter splashActivityPresenter);

    void inject(SplashFragment splashFragment);

    void inject(SplashPresenter splashPresenter);
}
