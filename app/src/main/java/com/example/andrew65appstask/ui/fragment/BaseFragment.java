package com.example.andrew65appstask.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.example.andrew65appstask.ui.Injector;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Базовый абстрактный класс для Fragment, связанных с Presenter
 */
public abstract class BaseFragment extends MvpAppCompatFragment implements Injector {

    private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
//        Icepick.restoreInstanceState(this, savedInstanceState);
    }

//    @Override
//    public void onSaveInstanceState(Bundle bundle) {
//        super.onSaveInstanceState(bundle);
//        Icepick.saveInstanceState(this, bundle);
//    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
