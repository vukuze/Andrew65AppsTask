package com.example.andrew65appstask.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface MainView extends MvpView {
    @StateStrategyType(SkipStrategy.class)
    void showSplashFragment();
}
