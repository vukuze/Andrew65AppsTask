package com.example.andrew65appstask.di;

import com.example.andrew65appstask.di.scope.SpecialtyScope;
import com.example.andrew65appstask.presentation.presenter.specialty.SpecialtyActivityPresenter;
import com.example.andrew65appstask.presentation.presenter.specialty.SpecialtyPresenter;
import com.example.andrew65appstask.ui.activity.specialty.SpecialtyActivity;
import com.example.andrew65appstask.ui.fragment.specialty.SpecialtyFragment;

import dagger.Subcomponent;

@SpecialtyScope
@Subcomponent
public interface SpecialtyComponent {
    void inject(SpecialtyActivity specialtyActivity);

    void inject(SpecialtyActivityPresenter specialtyActivityPresenter);

    void inject(SpecialtyFragment specialtyFragment);

    void inject(SpecialtyPresenter specialtyPresenter);
}
