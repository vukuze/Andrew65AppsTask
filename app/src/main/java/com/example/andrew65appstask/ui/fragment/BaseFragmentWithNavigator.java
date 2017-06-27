package com.example.andrew65appstask.ui.fragment;

import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;

/**
 * Базовый Fragment с Navigator
 */

public abstract class BaseFragmentWithNavigator extends BaseFragment {

    protected Navigator navigator;

    @Inject
    NavigatorHolder navigatorHolder;

    protected abstract Navigator createNavigator();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(this.getClass().getSimpleName(), "onCreate");
        inject();
        this.navigator = createNavigator();
    }

    @Override
    public void onResume() {
        Log.d(this.getClass().getSimpleName(), "onResume");
        super.onResume();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    public void onPause() {
        Log.d(this.getClass().getSimpleName(), "onPause");
        navigatorHolder.removeNavigator();
        super.onPause();
    }
}
