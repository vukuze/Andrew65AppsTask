package me.andrew.taskpersonnel.navigation;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.arellomobile.mvp.MvpAppCompatActivity;

import me.andrew.taskpersonnel.ui.fragment.employee.DetailsFragment;

/**
 * Navigator для DetailsActivity
 * Управляет всеми командами навигации
 */

public class DetailsActivityNavigator extends BaseActivityNavigator {

    public DetailsActivityNavigator(MvpAppCompatActivity activity, FragmentManager fragmentManager, int containerId) {
        super(activity, fragmentManager, containerId);
    }

    @Override
    protected Intent createActivityIntent(String screenKey, Object data) {
        return null;
    }

    @Override
    protected Fragment createFragment(String screenKey, Object data) {
        switch (screenKey) {
            case Screens.DETAILS_FRAGMENT:
                return DetailsFragment.newInstance((int) data);
            default:
                return null;
        }
    }
}