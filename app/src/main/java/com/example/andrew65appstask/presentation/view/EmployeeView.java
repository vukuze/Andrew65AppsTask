package com.example.andrew65appstask.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

@StateStrategyType(SingleStateStrategy.class)
public interface EmployeeView extends MvpView {
    void updateItems(List<com.example.andrew65appstask.data.Employee> employees);
}
