package com.example.andrew65appstask.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface SplashView extends MvpView {
    void updateItems(Iterable<com.example.andrew65appstask.employee.Employee> employees);

    void handleErrors(Throwable throwable);
}
