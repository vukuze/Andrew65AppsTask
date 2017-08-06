package com.example.andrew65appstask.di;

import com.example.andrew65appstask.di.module.UseCaseModule;
import com.example.andrew65appstask.di.scope.SplashScope;
import com.example.andrew65appstask.presentation.presenter.SplashPresenter;
import com.example.andrew65appstask.ui.fragment.SplashFragment;

import dagger.Subcomponent;

@SplashScope
@Subcomponent(modules = {UseCaseModule.class})
public interface SplashComponent {
    void inject(SplashFragment splashFragment);

    void inject(SplashPresenter splashPresenter);
}
