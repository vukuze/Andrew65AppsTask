package com.example.andrew65appstask.di;

import com.example.andrew65appstask.di.scope.DetailsScope;
import com.example.andrew65appstask.presentation.presenter.DetailsPresenter;
import com.example.andrew65appstask.ui.activity.DetailsActivity;
import com.example.andrew65appstask.ui.fragment.DetailsFragment;

import dagger.Subcomponent;

@DetailsScope
@Subcomponent
public interface DetailsComponent {
    void inject(DetailsActivity detailsActivity);

    void inject(DetailsPresenter detailsPresenter);

    void inject(DetailsFragment detailsFragment);
}