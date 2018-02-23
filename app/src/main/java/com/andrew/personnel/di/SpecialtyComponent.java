package com.andrew.personnel.di;

import com.andrew.personnel.di.scope.SpecialtyScope;
import com.andrew.personnel.presentation.presenter.specialty.SpecialtyActivityPresenter;
import com.andrew.personnel.presentation.presenter.specialty.SpecialtyPresenter;
import com.andrew.personnel.ui.activity.specialty.SpecialtyActivity;
import com.andrew.personnel.ui.fragment.specialty.SpecialtyFragment;

import dagger.Subcomponent;

@SpecialtyScope
@Subcomponent
public interface SpecialtyComponent {
    void inject(SpecialtyActivity specialtyActivity);

    void inject(SpecialtyActivityPresenter specialtyActivityPresenter);

    void inject(SpecialtyFragment specialtyFragment);

    void inject(SpecialtyPresenter specialtyPresenter);
}
