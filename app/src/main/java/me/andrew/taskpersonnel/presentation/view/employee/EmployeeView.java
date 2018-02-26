package me.andrew.taskpersonnel.presentation.view.employee;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

public interface EmployeeView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void updateItems(List<me.andrew.taskpersonnel.data.Employee> employees);
}
