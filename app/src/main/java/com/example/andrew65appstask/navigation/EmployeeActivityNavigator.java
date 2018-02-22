package com.example.andrew65appstask.navigation;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.andrew65appstask.R;
import com.example.andrew65appstask.ui.activity.employee.EmployeeActivity;
import com.example.andrew65appstask.ui.fragment.employee.DetailsFragment;
import com.example.andrew65appstask.ui.fragment.employee.EmployeeFragment;

import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Forward;

import static com.example.andrew65appstask.ui.fragment.employee.EmployeeFragment.EMPLOYEE_NOT_DEFINED;

/**
 * Navigator для EmployeeActivity
 * Управляет всеми командами навигации
 */

public class EmployeeActivityNavigator extends BaseActivityNavigator {

    private boolean isLandscape;

    public EmployeeActivityNavigator(MvpAppCompatActivity activity, FragmentManager fragmentManager, boolean isLandscape) {
        super(activity, fragmentManager, R.id.fragmentContainer);
        this.isLandscape = isLandscape;
    }

    @Override
    protected Fragment createFragment(String screenKey, Object data) {
        switch (screenKey) {
            case Screens.EMPLOYEE_FRAGMENT:
                return EmployeeFragment.newInstance((EmployeeFragment.RequestData) data);
            case Screens.DETAILS_FRAGMENT:
                return DetailsFragment.newInstance((int) data);
            default:
                return null;
        }
    }

    @Override
    public void applyCommand(Command command) {
        if (command instanceof Forward) {
            Forward cmd = (Forward) command;
            if (cmd.getScreenKey().equals(Screens.DETAILS_FRAGMENT)) {
                int employeeId = (int) cmd.getTransitionData();
                if (employeeId == EMPLOYEE_NOT_DEFINED) {
                    return;
                }
                ((EmployeeActivity) activity).setEmployeeId(employeeId);
                Fragment fragment = createFragment(cmd.getScreenKey(), cmd.getTransitionData());
                if (fragment == null) {
                    unknownScreen(command);
                    return;
                }
                if (isLandscape) {
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.detail_fragment_container, fragment)
                            .addToBackStack(Screens.DETAILS_FRAGMENT)
                            .commit();
                    return;
                }
            }
        }
        super.applyCommand(command);
    }

    @Override
    protected Intent createActivityIntent(String screenKey, Object data) {
        return null;
    }
}