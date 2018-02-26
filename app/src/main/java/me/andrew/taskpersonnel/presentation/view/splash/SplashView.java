package me.andrew.taskpersonnel.presentation.view.splash;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SkipStrategy.class)
public interface SplashView extends MvpView {
    void handleErrors(Throwable throwable);
}
