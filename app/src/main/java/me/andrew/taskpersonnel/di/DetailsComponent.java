package me.andrew.taskpersonnel.di;

import me.andrew.taskpersonnel.di.scope.DetailsScope;
import me.andrew.taskpersonnel.presentation.presenter.employee.DetailsPresenter;
import me.andrew.taskpersonnel.ui.fragment.employee.DetailsFragment;

import dagger.Subcomponent;

@DetailsScope
@Subcomponent
public interface DetailsComponent {
    void inject(DetailsPresenter detailsPresenter);

    void inject(DetailsFragment detailsFragment);
}
