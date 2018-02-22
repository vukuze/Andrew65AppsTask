package com.example.andrew65appstask.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.example.andrew65appstask.di.Injector;
import com.example.andrew65appstask.ui.activity.BaseActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Базовый фрагмент содержит ButterKnife, меняет ActionBar title
 */
public abstract class BaseFragment extends MvpAppCompatFragment
        implements Injector, BackButtonListener, ActionBarTitle {

    private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(this.getClass().getSimpleName(), "BaseFragment.onCreate");
        inject();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        BaseActivity activity = (BaseActivity) getActivity();
        if (activity != null && activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setTitle(setActionBarTitle());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(this.getClass().getSimpleName(), "BaseFragment.onDestroyView");
        unbinder.unbind();
    }
}
