package me.andrew.taskpersonnel.di;

import dagger.Subcomponent;
import me.andrew.taskpersonnel.di.module.splash.OkHttpClientModule;
import me.andrew.taskpersonnel.di.module.splash.RetrofitModule;
import me.andrew.taskpersonnel.di.module.splash.SplashUseCaseModule;
import me.andrew.taskpersonnel.di.scope.SplashScope;
import me.andrew.taskpersonnel.presentation.presenter.splash.SplashActivityPresenter;
import me.andrew.taskpersonnel.presentation.presenter.splash.SplashPresenter;
import me.andrew.taskpersonnel.ui.activity.splash.SplashActivity;
import me.andrew.taskpersonnel.ui.fragment.splash.SplashFragment;

@SplashScope
@Subcomponent(modules = {OkHttpClientModule.class, RetrofitModule.class, SplashUseCaseModule.class})
public interface SplashComponent {
    void inject(SplashActivity splashActivity);

    void inject(SplashActivityPresenter splashActivityPresenter);

    void inject(SplashFragment splashFragment);

    void inject(SplashPresenter splashPresenter);
}
