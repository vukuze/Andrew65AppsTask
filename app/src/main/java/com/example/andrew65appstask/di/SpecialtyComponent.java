package com.example.andrew65appstask.di;

import com.example.andrew65appstask.di.scope.SpecialtyScope;
import com.example.andrew65appstask.presentation.presenter.main.MainPresenter;
import com.example.andrew65appstask.presentation.presenter.specialty.SpecialtyPresenter;
import com.example.andrew65appstask.presentation.presenter.splash.SplashPresenter;
import com.example.andrew65appstask.ui.activity.main.MainActivity;
import com.example.andrew65appstask.ui.fragment.specialty.SpecialtyFragment;
import com.example.andrew65appstask.ui.fragment.splash.SplashFragment;

import dagger.Subcomponent;

@SpecialtyScope
@Subcomponent
public interface SpecialtyComponent {
    void inject(MainActivity mainActivity);

    void inject(MainPresenter mainPresenter);

    void inject(SpecialtyFragment specialtyFragment);

    void inject(SpecialtyPresenter specialtyPresenter);

    void inject(SplashFragment splashFragment);

    void inject(SplashPresenter splashPresenter);
}
