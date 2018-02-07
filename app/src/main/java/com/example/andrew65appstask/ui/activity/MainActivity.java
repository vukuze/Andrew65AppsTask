package com.example.andrew65appstask.ui.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.andrew65appstask.App;
import com.example.andrew65appstask.R;
import com.example.andrew65appstask.navigation.MainActivityNavigator;
import com.example.andrew65appstask.navigation.Screens;
import com.example.andrew65appstask.presentation.presenter.MainPresenter;
import com.example.andrew65appstask.presentation.view.MainView;
import com.example.andrew65appstask.ui.BackButtonListener;
import com.example.andrew65appstask.ui.fragment.SpecialtyFragment;
import com.example.andrew65appstask.ui.fragment.SplashFragment;

import javax.inject.Inject;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.commands.Replace;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    private static final String TAG = "MainActivity";

    protected Navigator navigator;

    @SuppressWarnings("unused")
    @InjectPresenter
    MainPresenter mainPresenter;

    @Inject
    NavigatorHolder navigatorHolder;

    private boolean fragmentAttached = false;

    @LayoutRes
    protected int getLayoutResId() {
        return R.layout.activity_fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        App.getSpecialtyComponent().inject(this);

        setContentView(getLayoutResId());

        this.navigator = new MainActivityNavigator(this, getSupportFragmentManager(), R.id.fragmentContainer);
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed");
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (fragment != null &&
                fragment instanceof BackButtonListener &&
                ((BackButtonListener) fragment).onBackPressed()) {
            return;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        navigatorHolder.removeNavigator();
        super.onPause();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        fragmentAttached = true;
        Log.d(TAG, "onAttachFragment, SplashFragment? " + (fragment instanceof SplashFragment) + ", SpecialtyFragment? " + (fragment instanceof SpecialtyFragment));
    }

    @Override
    public void setFragment() {
        Log.d(TAG, "setFragment");

        if (!fragmentAttached)
            navigator.applyCommand(new Replace(Screens.SPLASH_FRAGMENT, null));
    }

//    @Override
//    public void onEmployeeSelected(int employeeId) {
////        boolean isLandscape = findViewById(R.id.detail_fragment_container) != null;
////
////        if (isLandscape) {
////            navigator.applyCommand(new Replace(Screens.DETAILS_FRAGMENT, employeeId));
////        } else {
////            navigator.applyCommand(new Forward(Screens.DETAILS_FRAGMENT, employeeId));
////        }
//    }
}