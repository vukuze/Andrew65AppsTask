package me.andrew.taskpersonnel.presentation.view.employee;

import android.view.View;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

public interface EmployeeView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void updateItems(List<me.andrew.taskpersonnel.data.Employee> employees);

    @StateStrategyType(SkipStrategy.class)
    void updateScroll();

    @StateStrategyType(SkipStrategy.class)
    void updateChildAttachState(View childView);
}
