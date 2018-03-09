package me.andrew.taskpersonnel.ui.adapter.employee;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import me.andrew.taskpersonnel.ui.adapter.specialty.BaseSpecialtyAdapter;

public class SpecialtyAdapter extends BaseSpecialtyAdapter<BaseSpecialtyAdapter.BaseSpecialtyHolder> {

    @NonNull
    @Override
    public BaseSpecialtyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseSpecialtyHolder(inflate(parent, viewType));
    }
}