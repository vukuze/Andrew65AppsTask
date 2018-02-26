package me.andrew.taskpersonnel.di;

import me.andrew.taskpersonnel.di.scope.SpecialtyScope;
import me.andrew.taskpersonnel.presentation.presenter.specialty.SpecialtyActivityPresenter;
import me.andrew.taskpersonnel.presentation.presenter.specialty.SpecialtyPresenter;
import me.andrew.taskpersonnel.ui.activity.specialty.SpecialtyActivity;
import me.andrew.taskpersonnel.ui.fragment.specialty.SpecialtyFragment;

import dagger.Subcomponent;

@SpecialtyScope
@Subcomponent
public interface SpecialtyComponent {
    void inject(SpecialtyActivity specialtyActivity);

    void inject(SpecialtyActivityPresenter specialtyActivityPresenter);

    void inject(SpecialtyFragment specialtyFragment);

    void inject(SpecialtyPresenter specialtyPresenter);
}
