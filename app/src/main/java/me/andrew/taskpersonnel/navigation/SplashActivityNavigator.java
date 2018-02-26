package me.andrew.taskpersonnel.navigation;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.arellomobile.mvp.MvpAppCompatActivity;
import me.andrew.taskpersonnel.ui.activity.specialty.SpecialtyActivity;
import me.andrew.taskpersonnel.ui.fragment.splash.SplashFragment;

/**
 * Navigator для SplashActivity
 * Управляет всеми командами навигации
 */

public class SplashActivityNavigator extends BaseActivityNavigator {

    public SplashActivityNavigator(MvpAppCompatActivity activity, FragmentManager fragmentManager, int containerId) {
        super(activity, fragmentManager, containerId);
    }

    @Override
    protected Intent createActivityIntent(String screenKey, Object data) {
        switch (screenKey) {
            case Screens.SPECIALTY_ACTIVITY:
                return SpecialtyActivity.newIntent(activity);
            default:
                return null;
        }
    }

    @Override
    protected Fragment createFragment(String screenKey, Object data) {
        switch (screenKey) {
            case Screens.SPLASH_FRAGMENT:
                return SplashFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    protected void exit() {
        super.exit();
    }
}