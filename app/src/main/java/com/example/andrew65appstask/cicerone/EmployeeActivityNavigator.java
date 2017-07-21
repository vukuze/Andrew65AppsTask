package com.example.andrew65appstask.cicerone;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.example.andrew65appstask.R;
import com.example.andrew65appstask.ui.activity.DetailsActivity;
import com.example.andrew65appstask.ui.fragment.DetailsFragment;
import com.example.andrew65appstask.ui.fragment.EmployeeFragment;

import ru.terrakok.cicerone.android.SupportAppNavigator;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Replace;

/**
 * Navigator для EmployeeActivity
 */

public class EmployeeActivityNavigator extends SupportAppNavigator {

    private FragmentActivity activity;

    public EmployeeActivityNavigator(FragmentActivity activity, int containerId) {
        super(activity, containerId);
        this.activity = activity;
    }

    @Override
    protected Fragment createFragment(String screenKey, Object data) {
        if (screenKey.equals(Screens.EMPLOYEE_FRAGMENT)) {
            return EmployeeFragment.newInstance((int) data);
        }
        return null;
    }

    @Override
    protected Intent createActivityIntent(String screenKey, Object data) {
        if (screenKey.equals(Screens.DETAILS_ACTIVITY)) {
            return DetailsActivity.newIntent(activity, (int) data);
        }
        return null;
    }

    @Override
    public void applyCommand(Command command) {
        if (command instanceof Replace) {
            Replace cmd = (Replace) command;
            if (cmd.getScreenKey().equals(Screens.DETAILS_FRAGMENT)) {
                int employeeId = (int) cmd.getTransitionData();
                Fragment newDetail = DetailsFragment.newInstance(employeeId);
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.detail_fragment_container, newDetail)
                        .commit();
                return;
            }
        }
        super.applyCommand(command);
    }

//    private void createDetailsFragment(Replace cmd) {
//        Fragment newDetail = DetailsFragment.newInstance((int) cmd.getTransitionData());
//        activity.getSupportFragmentManager().beginTransaction()
//                .replace(R.id.detail_fragment_container, newDetail)
//                .commit();
//    }
}