package com.example.andrew65appstask.dagger;

import com.example.andrew65appstask.dagger.module.NetworkModule;
import com.example.andrew65appstask.dagger.module.RetrofitModule;
import com.example.andrew65appstask.dagger.scope.SplashScope;
import com.example.andrew65appstask.domain.GetNetworkData;
import com.example.andrew65appstask.presentation.presenter.SplashPresenter;
import com.example.andrew65appstask.ui.fragment.SplashFragment;

import dagger.Subcomponent;

@SplashScope
@Subcomponent(modules = {NetworkModule.class, RetrofitModule.class})
public interface SplashComponent {
    void inject(SplashFragment splashFragment);

    void inject(SplashPresenter splashPresenter);

    void inject(GetNetworkData networkData);
}
