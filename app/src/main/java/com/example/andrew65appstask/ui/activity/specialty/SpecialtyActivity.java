package com.example.andrew65appstask.ui.activity.specialty;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.andrew65appstask.App;
import com.example.andrew65appstask.R;
import com.example.andrew65appstask.navigation.SpecialtyActivityNavigator;
import com.example.andrew65appstask.presentation.presenter.specialty.SpecialtyActivityPresenter;
import com.example.andrew65appstask.presentation.view.specialty.SpecialtyActivityView;
import com.example.andrew65appstask.ui.activity.BaseActivity;

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