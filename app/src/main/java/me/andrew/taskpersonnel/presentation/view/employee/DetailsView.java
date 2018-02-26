package me.andrew.taskpersonnel.presentation.view.employee;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SingleStateStrategy.class)
public interface DetailsView extends MvpView {
    void updateItems(me.andrew.taskpersonnel.data.Employee employee);
}
