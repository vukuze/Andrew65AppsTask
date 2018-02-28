package me.andrew.taskpersonnel.navigation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;

import me.andrew.taskpersonnel.R;
import me.andrew.taskpersonnel.ui.fragment.BackButtonListener;
import ru.terrakok.cicerone.android.SupportAppNavigator;
import ru.terrakok.cicerone.commands.Back;
import ru.terrakok.cicerone.commands.BackTo;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Forward;
import ru.terrakok.cicerone.commands.Replace;

public abstract class BaseActivityNavigator extends SupportAppNavigator {

    protected MvpAppCompatActivity activity;
    private FragmentManager fragmentManager;

    BaseActivityNavigator(MvpAppCompatActivity activity, FragmentManager fragmentManager, int containerId) {
        super(activity, fragmentManager, containerId);
        this.activity = activity;
        this.fragmentManager = fragmentManager;
    }

    @Override
    protected void showSystemMessage(String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void applyCommand(Command command) {
        String screenKey = null;
        if (command instanceof Forward) {
            screenKey = ((Forward) command).getScreenKey();
        } else if (command instanceof Replace) {
            screenKey = ((Replace) command).getScreenKey();
        } else if (command instanceof BackTo) {
            screenKey = ((BackTo) command).getScreenKey();
        }
        Log.d(this.getClass().getSimpleName(), "applyCommand " + command.getClass().getSimpleName() +
                (screenKey != null ? ", screenKey: " + screenKey : ""));

        if (command instanceof Back) {
            onBackPressed(R.id.fragmentContainer);
        }
        super.applyCommand(command);
    }

    void onBackPressed(int id) {
        Fragment fragment = fragmentManager.findFragmentById(id);
        if (fragment != null && fragment instanceof BackButtonListener) {
            Log.d(this.getClass().getSimpleName(), "Back - " + fragment.getClass().getSimpleName());
            ((BackButtonListener) fragment).onBackPressed();
        }
    }
}