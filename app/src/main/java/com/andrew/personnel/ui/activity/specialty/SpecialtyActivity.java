package com.andrew.personnel.ui.activity.specialty;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.andrew.personnel.App;
import com.andrew.personnel.R;
import com.andrew.personnel.navigation.SpecialtyActivityNavigator;
import com.andrew.personnel.presentation.presenter.specialty.SpecialtyActivityPresenter;
import com.andrew.personnel.presentation.view.specialty.SpecialtyActivityView;
import com.andrew.personnel.ui.activity.BaseActivity;

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