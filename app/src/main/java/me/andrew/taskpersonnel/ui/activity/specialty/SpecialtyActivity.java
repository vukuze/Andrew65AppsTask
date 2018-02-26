package me.andrew.taskpersonnel.ui.activity.specialty;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;

import me.andrew.taskpersonnel.App;
import me.andrew.taskpersonnel.R;
import me.andrew.taskpersonnel.navigation.SpecialtyActivityNavigator;
import me.andrew.taskpersonnel.presentation.presenter.specialty.SpecialtyActivityPresenter;
import me.andrew.taskpersonnel.presentation.view.specialty.SpecialtyActivityView;
import me.andrew.taskpersonnel.ui.activity.BaseActivity;

public class SpecialtyActivity extends BaseActivity implements SpecialtyActivityView {

    @SuppressWarnings("unused")
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.navigator = new SpecialtyActivityNavigator(this, getSupportFragmentManager(), R.id.fragmentContainer);
    }
}