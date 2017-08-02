package com.example.andrew65appstask.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.andrew65appstask.App;
import com.example.andrew65appstask.R;
import com.example.andrew65appstask.navigation.Screens;
import com.example.andrew65appstask.ui.fragment.DetailsFragment;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;
import ru.terrakok.cicerone.commands.Replace;

public class DetailsActivity extends BaseActivity {

    private static final String EXTRA_EMPLOYEE_ID = "employee_id";

    public static Intent newIntent(Context context, int employeeId) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(EXTRA_EMPLOYEE_ID, employeeId);
        return intent;
    }

    @Override
    protected Navigator createNavigator() {
        return new SupportFragmentNavigator(getSupportFragmentManager(), R.id.fragmentContainer) {
            @Override
            protected Fragment createFragment(String screenKey, Object data) {
                int employeeId = getIntent().getIntExtra(EXTRA_EMPLOYEE_ID, 0);
                return DetailsFragment.newInstance(employeeId);
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
        App.getDetailsComponent().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int employeeId = getIntent().getIntExtra(EXTRA_EMPLOYEE_ID, 0);
        navigator.applyCommand(new Replace(Screens.DETAILS_FRAGMENT, employeeId));
    }
}
