package me.andrew.taskpersonnel.ui.activity.specialty;

import android.content.Context;
import android.content.Intent;

import com.arellomobile.mvp.presenter.InjectPresenter;

import me.andrew.taskpersonnel.App;
import me.andrew.taskpersonnel.R;
import me.andrew.taskpersonnel.navigation.SpecialtyActivityNavigator;
import me.andrew.taskpersonnel.presentation.presenter.specialty.SpecialtyActivityPresenter;
import me.andrew.taskpersonnel.presentation.view.specialty.SpecialtyActivityView;
import me.andrew.taskpersonnel.ui.activity.BaseActivity;
import ru.terrakok.cicerone.Navigator;

public class SpecialtyActivity extends BaseActivity implements SpecialtyActivityView {

    @InjectPresenter
    SpecialtyActivityPresenter specialtyActivityPresenter;

    public static Intent newIntent(Context context) {
        return new Intent(context, SpecialtyActivity.class);
    }

    @Override
    public void inject() {
        App.getSpecialtyComponent().inject(this);
    }

    @Override
    public Navigator createNavigator() {
        return new SpecialtyActivityNavigator(this, getSupportFragmentManager(), R.id.fragmentContainer);
    }

    @Override
    public void onBackPressed() {
        specialtyActivityPresenter.onBackCommandClick();
    }
}