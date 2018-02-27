package me.andrew.taskpersonnel.di;

import dagger.Subcomponent;
import me.andrew.taskpersonnel.di.scope.DetailsScope;
import me.andrew.taskpersonnel.presentation.presenter.employee.DetailsActivityPresenter;
import me.andrew.taskpersonnel.presentation.presenter.employee.DetailsPresenter;
import me.andrew.taskpersonnel.ui.activity.employee.DetailsActivity;
import me.andrew.taskpersonnel.ui.fragment.employee.DetailsFragment;

@DetailsScope
@Subcomponent
public interface DetailsComponent {
    void inject(DetailsActivity detailsActivity);

    void inject(DetailsActivityPresenter detailsActivityPresenter);

    void inject(DetailsFragment detailsFragment);

    void inject(DetailsPresenter detailsPresenter);
}
