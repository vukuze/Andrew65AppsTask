package com.example.andrew65appstask.dagger;

import com.example.andrew65appstask.dagger.scope.SpecialtyScope;
import com.example.andrew65appstask.presentation.presenter.SpecialtyPresenter;
import com.example.andrew65appstask.ui.activity.SpecialtyActivity;
import com.example.andrew65appstask.ui.fragment.SpecialtyFragment;

import dagger.Subcomponent;

@SpecialtyScope
@Subcomponent
public interface SpecialtyComponent {
    void inject(SpecialtyActivity specialtyActivity);

    void inject(SpecialtyFragment specialtyFragment);

    void inject(SpecialtyPresenter specialtyPresenter);
}
