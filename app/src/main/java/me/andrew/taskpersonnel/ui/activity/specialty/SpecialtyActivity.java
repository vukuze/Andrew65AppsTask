package me.andrew.taskpersonnel.ui.activity.specialty;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.arellomobile.mvp.presenter.InjectPresenter;

import me.andrew.taskpersonnel.App;
import me.andrew.taskpersonnel.R;
import me.andrew.taskpersonnel.navigation.SpecialtyActivityNavigator;
import me.andrew.taskpersonnel.presentation.presenter.specialty.SpecialtyActivityPresenter;
import me.andrew.taskpersonnel.presentation.view.specialty.SpecialtyActivityView;
import me.andrew.taskpersonnel.ui.activity.BaseActivity;
import ru.terrakok.cicerone.commands.SystemMessage;

public class SpecialtyActivity extends BaseActivity implements SpecialtyActivityView {

    private static final String DOUBLE_BACKPRESS_TEXT = "Для выхода нажмите еще раз";

    @InjectPresenter
    SpecialtyActivityPresenter specialtyActivityPresenter;

    private boolean backPressedOnce = false;

    public static Intent newIntent(Context context) {
        return new Intent(context, SpecialtyActivity.class);
    }

    @Override
    public void inject() {
        App.getSpecialtyComponent().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.navigator = new SpecialtyActivityNavigator(this, getSupportFragmentManager(), R.id.fragmentContainer);
    }

    @Override
    public void onBackPressed() {
        if (backPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.backPressedOnce = true;
        navigator.applyCommand(new SystemMessage(DOUBLE_BACKPRESS_TEXT));

        new Handler().postDelayed(() -> backPressedOnce = false, 2000);
    }
}