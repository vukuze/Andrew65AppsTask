package com.example.andrew65appstask.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.andrew65appstask.App;
import com.example.andrew65appstask.R;
import com.example.andrew65appstask.Screens;
import com.example.andrew65appstask.ui.fragment.SplashFragment;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;
import ru.terrakok.cicerone.commands.Forward;

public class SplashActivity extends SingleFragmentActivity1 {

    @Override
    protected Navigator createNavigator() {
        return new SupportFragmentNavigator(getSupportFragmentManager(), R.id.fragmentContainer) {
            @Override
            protected Fragment createFragment(String screenKey, Object data) {
                if (screenKey.equals(Screens.SPLASH_FRAGMENT))
                    return SplashFragment.newInstance();
                return null;
            }

            @Override
            protected void showSystemMessage(String message) {

            }

            @Override
            protected void exit() {
                finish();
            }

        };
    }

    @Override
    public void inject() {
        App.getSplashComponent().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigator.applyCommand(new Forward(Screens.SPLASH_FRAGMENT, null));
    }

}