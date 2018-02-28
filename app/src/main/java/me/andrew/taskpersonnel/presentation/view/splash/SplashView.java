package me.andrew.taskpersonnel.presentation.view.splash;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface SplashView extends MvpView {
    @StateStrategyType(SingleStateStrategy.class)
    void setIsViewLoading(boolean isVisible);
}
