package com.andrew.personnel.di;

import com.andrew.personnel.di.scope.DetailsScope;
import com.andrew.personnel.presentation.presenter.employee.DetailsPresenter;
import com.andrew.personnel.ui.fragment.employee.DetailsFragment;

import dagger.Subcomponent;

@DetailsScope
@Subcomponent
public interface DetailsComponent {
    void inject(DetailsPresenter detailsPresenter);

    void inject(DetailsFragment detailsFragment);
}
