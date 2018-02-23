package com.andrew.personnel.presentation.view.employee;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

public interface EmployeeView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void updateItems(List<com.andrew.personnel.data.Employee> employees);
}
