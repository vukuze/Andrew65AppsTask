package com.example.andrew65appstask.presentation.presenter.specialty;

import com.example.andrew65appstask.App;
import com.example.andrew65appstask.navigation.Screens;
import com.example.andrew65appstask.presentation.presenter.BasePresenter;
import com.example.andrew65appstask.presentation.view.specialty.SpecialtyActivityView;

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
