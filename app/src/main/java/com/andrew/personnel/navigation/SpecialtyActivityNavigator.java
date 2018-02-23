package com.andrew.personnel.navigation;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.andrew.personnel.ui.activity.employee.EmployeeActivity;
import com.andrew.personnel.ui.fragment.specialty.SpecialtyFragment;

/**
 * Navigator для SpecialtyActivity
 * Управляет всеми командами навигации
 */

public class SpecialtyActivityNavigator extends BaseActivityNavigator {

    public SpecialtyActivityNavigator(MvpAppCompatActivity activity, FragmentManager fragmentManager, int containerId) {
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
            default:
                return null;
        }
    }
}