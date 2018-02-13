package com.example.andrew65appstask.ui.adapter.specialty;

import android.util.Log;
import android.view.View;

import com.example.andrew65appstask.presentation.presenter.SpecialtyPresenter;

/**
 * SpecialtyHolder, в котором implements View.OnClickListener
 */

public class SpecialtyHolder extends BaseSpecialtyHolder implements View.OnClickListener {

    private static final String TAG = "SpecialtyHolder";

    private SpecialtyPresenter specialtyPresenter;

    SpecialtyHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.specialtyPresenter = new SpecialtyPresenter();
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick " + specialtyPresenter);
        specialtyPresenter.onClick(specialty.getId());
    }
}
