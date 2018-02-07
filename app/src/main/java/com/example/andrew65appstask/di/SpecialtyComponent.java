package com.example.andrew65appstask.di;

import com.example.andrew65appstask.di.scope.SpecialtyScope;
import com.example.andrew65appstask.presentation.presenter.MainPresenter;
import com.example.andrew65appstask.presentation.presenter.SpecialtyPresenter;
import com.example.andrew65appstask.presentation.presenter.SplashPresenter;
import com.example.andrew65appstask.ui.activity.MainActivity;
import com.example.andrew65appstask.ui.fragment.SpecialtyFragment;
import com.example.andrew65appstask.ui.fragment.SplashFragment;

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
