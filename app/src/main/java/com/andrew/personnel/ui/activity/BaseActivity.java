package com.andrew.personnel.ui.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.util.Log;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.andrew.personnel.R;
import com.andrew.personnel.di.Injector;

import javax.inject.Inject;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.commands.Back;

public abstract class BaseActivity extends MvpAppCompatActivity implements Injector {

    protected Navigator navigator;

    @Inject
    NavigatorHolder navigatorHolder;

    @LayoutRes
    protected int getLayoutResId() {
        return R.layout.activity_fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(this.getClass().getSimpleName(), "onCreate");

        inject();

        setContentView(getLayoutResId());
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        navigatorHolder.removeNavigator();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        Log.d(this.getClass().getSimpleName(), "onBackPressed");
        navigator.applyCommand(new Back());
    }
}
