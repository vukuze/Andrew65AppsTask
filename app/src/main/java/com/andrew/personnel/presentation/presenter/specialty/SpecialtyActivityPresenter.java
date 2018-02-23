package com.andrew.personnel.presentation.presenter.specialty;

import com.andrew.personnel.App;
import com.andrew.personnel.navigation.Screens;
import com.andrew.personnel.presentation.presenter.BasePresenter;
import com.andrew.personnel.presentation.view.specialty.SpecialtyActivityView;

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
