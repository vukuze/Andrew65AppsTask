package com.andrew.personnel.presentation.view.employee;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SingleStateStrategy.class)
public interface DetailsView extends MvpView {
    void updateItems(com.andrew.personnel.data.Employee employee);
}
