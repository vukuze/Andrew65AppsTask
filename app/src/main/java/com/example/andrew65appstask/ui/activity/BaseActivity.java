package com.example.andrew65appstask.ui.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.andrew65appstask.R;
import com.example.andrew65appstask.ui.BackButtonListener;
import com.example.andrew65appstask.dagger.Injector;

import javax.inject.Inject;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;

public abstract class BaseActivity extends AppCompatActivity implements Injector {

    protected Navigator navigator;

    @Inject
    NavigatorHolder navigatorHolder;

    @LayoutRes
    protected int getLayoutResId() {
        return R.layout.activity_fragment;
    }

    protected abstract Navigator createNavigator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(this.getClass().getSimpleName(), "onCreate");
        setContentView(getLayoutResId());
        inject();
        this.navigator = createNavigator();
    }

    @Override
    public void onBackPressed() {
        Log.d(this.getClass().getSimpleName(), "onBackPressed");
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (fragment != null &&
                fragment instanceof BackButtonListener &&
                ((BackButtonListener) fragment).onBackPressed()) {
            return;
        } else {
            super.onBackPressed();
        }
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
}
