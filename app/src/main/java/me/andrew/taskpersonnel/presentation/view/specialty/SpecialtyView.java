package me.andrew.taskpersonnel.presentation.view.specialty;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

public interface SpecialtyView extends MvpView {
    @StateStrategyType(SingleStateStrategy.class)
    void updateItems(List<me.andrew.taskpersonnel.data.Specialty> specialties);
}
