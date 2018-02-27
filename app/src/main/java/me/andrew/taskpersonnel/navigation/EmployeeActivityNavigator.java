package me.andrew.taskpersonnel.navigation;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.arellomobile.mvp.MvpAppCompatActivity;

import me.andrew.taskpersonnel.R;
import me.andrew.taskpersonnel.ui.activity.employee.DetailsActivity;
import me.andrew.taskpersonnel.ui.fragment.employee.DetailsFragment;
import me.andrew.taskpersonnel.ui.fragment.employee.EmployeeFragment;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Forward;
import ru.terrakok.cicerone.commands.Replace;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;
import static me.andrew.taskpersonnel.ui.fragment.employee.EmployeeFragment.EMPLOYEE_NOT_DEFINED;

/**
 * Navigator для EmployeeActivity
 * Управляет всеми командами навигации
 */
public class EmployeeActivityNavigator extends BaseActivityNavigator {

    private boolean isLandscape;

    public EmployeeActivityNavigator(MvpAppCompatActivity activity, FragmentManager fragmentManager) {
        super(activity, fragmentManager, R.id.fragmentContainer);
        this.isLandscape = activity.getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE;
    }

    @Override
    protected Intent createActivityIntent(String screenKey, Object data) {
        switch (screenKey) {
            case Screens.DETAILS_ACTIVITY:
                return DetailsActivity.newIntent(activity, (int) data);
            default:
                return null;
        }
    }

    @Override
    protected Fragment createFragment(String screenKey, Object data) {
        switch (screenKey) {
            case Screens.EMPLOYEE_FRAGMENT:
                return EmployeeFragment.newInstance((int) data);
            case Screens.DETAILS_FRAGMENT:
                return DetailsFragment.newInstance((int) data);
            default:
                return null;
        }
    }

    @Override
    public void applyCommand(Command command) {
        if (command instanceof Replace) {
            Replace cmd = (Replace) command;
            if (cmd.getScreenKey().equals(Screens.DETAILS_FRAGMENT)) {
                int employeeId = (int) cmd.getTransitionData();
                if (employeeId == EMPLOYEE_NOT_DEFINED) {
                    return;
                }
                if (isLandscape) {
                    Fragment fragment = createFragment(Screens.DETAILS_FRAGMENT, employeeId);
                    if (fragment == null) {
                        unknownScreen(command);
                        return;
                    }
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.detail_fragment_container, fragment)
                            .commit();
                    return;
                } else {
                    command = new Forward(Screens.DETAILS_ACTIVITY, employeeId);
                }
            }
        }
        super.applyCommand(command);
    }
}