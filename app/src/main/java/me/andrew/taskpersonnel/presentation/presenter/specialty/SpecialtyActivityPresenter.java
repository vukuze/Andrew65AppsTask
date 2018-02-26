package me.andrew.taskpersonnel.presentation.presenter.specialty;

import me.andrew.taskpersonnel.App;
import me.andrew.taskpersonnel.navigation.Screens;
import me.andrew.taskpersonnel.presentation.presenter.BasePresenter;
import me.andrew.taskpersonnel.presentation.view.specialty.SpecialtyActivityView;

public class SpecialtyActivityPresenter extends BasePresenter<SpecialtyActivityView> {

    @Override
    public void inject() {
        App.getSpecialtyComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        router.replaceScreen(Screens.SPECIALTY_FRAGMENT, null);
    }
}
