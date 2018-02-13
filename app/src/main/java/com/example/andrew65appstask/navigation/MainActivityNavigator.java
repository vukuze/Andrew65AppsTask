package com.example.andrew65appstask.navigation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;

import com.example.andrew65appstask.ui.activity.MainActivity;
import com.example.andrew65appstask.ui.fragment.EmployeeFragment;
import com.example.andrew65appstask.ui.fragment.SpecialtyFragment;
import com.example.andrew65appstask.ui.fragment.SplashFragment;

import java.util.List;

import ru.terrakok.cicerone.android.SupportFragmentNavigator;
import ru.terrakok.cicerone.commands.Back;
import ru.terrakok.cicerone.commands.Command;

/**
 * Navigator для MainActivity
 * Управляет созданием фрагментов
 */

public class MainActivityNavigator extends SupportFragmentNavigator {

    private MainActivity activity;
    private FragmentManager fragmentManager;

    public MainActivityNavigator(MainActivity activity, FragmentManager fragmentManager, int containerId) {
        super(fragmentManager, containerId);
        this.activity = activity;
        this.fragmentManager = fragmentManager;
    }

    @Override
    protected Fragment createFragment(String screenKey, Object data) {
        if (screenKey.equals(Screens.SPECIALTY_FRAGMENT))
            return SpecialtyFragment.newInstance();
        if (screenKey.equals(Screens.SPLASH_FRAGMENT))
            return SplashFragment.newInstance();
        if (screenKey.equals(Screens.EMPLOYEE_FRAGMENT))
            return EmployeeFragment.newInstance((int) data);
        return null;
    }

    @Override
    protected void showSystemMessage(String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void exit() {
        Log.d(this.getClass().getSimpleName(), "exit");

        activity.finish();
    }

    @Override
    public void applyCommand(Command command) {
        Log.d(this.getClass().getSimpleName(), "applyCommand " + command.getClass().getSimpleName());

        if (command instanceof Back) {
            List<Fragment> fragments = fragmentManager.getFragments();
            int fragmentCount = fragments.size();
            if (fragmentCount > 0) {
                Fragment fragment = fragments.get(fragmentCount - 1);
                Log.d(this.getClass().getSimpleName(), "exit - " + fragment.getClass().getSimpleName() + ", fragmentCount = " + fragmentCount);

                if (fragment instanceof SplashFragment)
                    ((SplashFragment) fragment).onBackPressed();
                if (fragment instanceof SpecialtyFragment)
                    ((SpecialtyFragment) fragment).onBackPressed();
            }
        }

        super.applyCommand(command);
    }
}