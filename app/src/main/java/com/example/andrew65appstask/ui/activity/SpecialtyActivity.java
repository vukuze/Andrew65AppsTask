package com.example.andrew65appstask.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.andrew65appstask.App;
import com.example.andrew65appstask.R;
import com.example.andrew65appstask.Screens;
import com.example.andrew65appstask.ui.fragment.SpecialtyFragment;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;
import ru.terrakok.cicerone.commands.Forward;

public class SpecialtyActivity extends SingleFragmentActivity1 {

    public static Intent newIntent(Context context) {
        return new Intent(context, SpecialtyActivity.class);
    }

    @Override
    protected Navigator createNavigator() {
        return new SupportFragmentNavigator(getSupportFragmentManager(), R.id.fragmentContainer) {
            @Override
            protected Fragment createFragment(String screenKey, Object data) {
                if (screenKey.equals(Screens.SPECIALTY_FRAGMENT))
                    return SpecialtyFragment.newInstance();
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
        App.getSpecialtyComponent().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigator.applyCommand(new Forward(Screens.SPECIALTY_FRAGMENT, null));
    }
}
