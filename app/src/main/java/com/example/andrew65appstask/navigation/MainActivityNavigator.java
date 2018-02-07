package com.example.andrew65appstask.navigation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.andrew65appstask.ui.activity.MainActivity;
import com.example.andrew65appstask.ui.fragment.EmployeeFragment;
import com.example.andrew65appstask.ui.fragment.SpecialtyFragment;
import com.example.andrew65appstask.ui.fragment.SplashFragment;

import ru.terrakok.cicerone.android.SupportFragmentNavigator;

/**
 * Navigator для MainActivity
 * Управляет фрагментами
 */

public class MainActivityNavigator extends SupportFragmentNavigator {

    private MainActivity activity;

    public MainActivityNavigator(MainActivity activity, FragmentManager fragmentManager, int containerId) {
        super(fragmentManager, containerId);
        this.activity = activity;
    }

    @Override
    protected Fragment createFragment(String screenKey, Object data) {
        if (screenKey.equals(Screens.SPECIALTY_FRAGMENT))
            return SpecialtyFragment.newInstance();
        if (screenKey.equals(Screens.SPLASH_FRAGMENT))
            return SplashFragment.newInstance();
        if (screenKey.equals(Screens.EMPLOYEE_FRAGMENT)) {
            return EmployeeFragment.newInstance((int) data);
        }
        return null;
    }

    @Override
    protected void showSystemMessage(String message) {
    }

    @Override
    protected void exit() {
        activity.finish();
    }
}