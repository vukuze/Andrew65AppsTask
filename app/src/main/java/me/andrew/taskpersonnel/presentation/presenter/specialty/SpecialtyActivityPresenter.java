package me.andrew.taskpersonnel.presentation.presenter.specialty;

import android.os.Handler;

import me.andrew.taskpersonnel.App;
import me.andrew.taskpersonnel.navigation.Screens;
import me.andrew.taskpersonnel.presentation.presenter.BasePresenter;
import me.andrew.taskpersonnel.presentation.view.specialty.SpecialtyActivityView;

public class SpecialtyActivityPresenter extends BasePresenter<SpecialtyActivityView> {

    private static final String NEED_DOUBLE_BACKPRESS_TEXT = "Для выхода нажмите еще раз";

    private boolean backPressedOnce = false;

    @Override
    public void inject() {
        App.getSpecialtyComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        router.replaceScreen(Screens.SPECIALTY_FRAGMENT, null);
    }

    @Override
    public void onBackCommandClick() {
        if (backPressedOnce) {
            super.onBackCommandClick();
            router.exit();
            return;
        }

        this.backPressedOnce = true;
        router.showSystemMessage(NEED_DOUBLE_BACKPRESS_TEXT);

        new Handler().postDelayed(() -> backPressedOnce = false, 3000);
    }
}
