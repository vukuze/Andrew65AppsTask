package com.example.andrew65appstask.presentation.view.employee;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

public interface EmployeeView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void updateItems(List<com.example.andrew65appstask.data.Employee> employees);
}
