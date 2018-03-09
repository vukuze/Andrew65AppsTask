package me.andrew.taskpersonnel.ui.adapter.specialty;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import me.andrew.taskpersonnel.presentation.presenter.specialty.SpecialtyPresenter;

/**
 * SpecialtyAdapter с реализацией View.OnClickListener в SpecialtyHolder
 */
public class SpecialtyAdapter extends BaseSpecialtyAdapter<SpecialtyAdapter.SpecialtyHolder> {

    private SpecialtyPresenter specialtyPresenter;

    public SpecialtyAdapter(SpecialtyPresenter specialtyPresenter) {
        this.specialtyPresenter = specialtyPresenter;
    }

    @NonNull
    @Override
    public SpecialtyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SpecialtyHolder(inflate(parent, viewType));
    }

    /**
     * SpecialtyHolder, в котором implements View.OnClickListener
     */
    class SpecialtyHolder extends BaseSpecialtyAdapter.BaseSpecialtyHolder implements View.OnClickListener {

        SpecialtyHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            specialtyPresenter.onClick(specialty.getId());
        }
    }
}