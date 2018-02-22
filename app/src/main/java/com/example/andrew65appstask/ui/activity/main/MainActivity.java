package com.example.andrew65appstask.ui.activity.main;

import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.andrew65appstask.App;
import com.example.andrew65appstask.R;
import com.example.andrew65appstask.navigation.MainActivityNavigator;
import com.example.andrew65appstask.presentation.presenter.main.MainPresenter;
import com.example.andrew65appstask.presentation.view.main.MainView;
import com.example.andrew65appstask.ui.activity.BaseActivity;

public class MainActivity extends BaseActivity implements MainView {

    @SuppressWarnings("unused")
    @InjectPresenter
    MainPresenter mainPresenter;

    @Override
    public void inject() {
        App.getSpecialtyComponent().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.navigator = new MainActivityNavigator(this, getSupportFragmentManager(), R.id.fragmentContainer);
    }
}