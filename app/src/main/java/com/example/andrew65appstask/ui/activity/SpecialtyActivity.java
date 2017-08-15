package com.example.andrew65appstask.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.andrew65appstask.App;
import com.example.andrew65appstask.R;
import com.example.andrew65appstask.navigation.Screens;
import com.example.andrew65appstask.ui.fragment.SpecialtyFragment;
import com.example.andrew65appstask.ui.fragment.SplashFragment;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.SupportAppNavigator;
import ru.terrakok.cicerone.commands.Replace;

public class SpecialtyActivity extends BaseActivity {

    @Override
    protected Navigator createNavigator() {
        return new SupportAppNavigator(this, R.id.fragmentContainer) {
            @Override
            protected Intent createActivityIntent(String screenKey, Object data) {
                if (screenKey.equals(Screens.EMPLOYEE_ACTIVITY))
                    return EmployeeActivity.newIntent(SpecialtyActivity.this, (int) data);
                return null;
            }

            @Override
            protected Fragment createFragment(String screenKey, Object data) {
                if (screenKey.equals(Screens.SPECIALTY_FRAGMENT))
                    return SpecialtyFragment.newInstance();
                if (screenKey.equals(Screens.SPLASH_FRAGMENT))
                    return SplashFragment.newInstance();
                return null;
            }
        };
    }

    @Override
    public void inject() {
        App.getSpecialtyComponent().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigator.applyCommand(new Replace(Screens.SPLASH_FRAGMENT, null));
    }
}
