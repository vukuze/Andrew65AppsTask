package com.example.andrew65appstask.navigation;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.andrew65appstask.ui.activity.employee.EmployeeActivity;
import com.example.andrew65appstask.ui.fragment.specialty.SpecialtyFragment;
import com.example.andrew65appstask.ui.fragment.splash.SplashFragment;

/**
 * Navigator для MainActivity
 * Управляет всеми командами навигации
 */

public class MainActivityNavigator extends BaseActivityNavigator {

    public MainActivityNavigator(MvpAppCompatActivity activity, FragmentManager fragmentManager, int containerId) {
        super(activity, fragmentManager, containerId);
    }

    @Override
    protected Intent createActivityIntent(String screenKey, Object data) {
        switch (screenKey) {
            case Screens.EMPLOYEE_ACTIVITY:
                return EmployeeActivity.newIntent(activity, (int) data);
            default:
                return null;
        }
    }

    @Override
    protected Fragment createFragment(String screenKey, Object data) {
        switch (screenKey) {
            case Screens.SPECIALTY_FRAGMENT:
                return SpecialtyFragment.newInstance();
            case Screens.SPLASH_FRAGMENT:
                return SplashFragment.newInstance();
            default:
                return null;
        }
    }
}