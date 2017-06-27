package com.example.andrew65appstask.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SingleStateStrategy.class)
public interface DetailsView extends MvpView {
    void updateItems(com.example.andrew65appstask.employee.Employee employee);
}
