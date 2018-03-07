package me.andrew.taskpersonnel.ui.adapter.employee;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import me.andrew.taskpersonnel.ui.adapter.specialty.BaseSpecialtyAdapter;
import me.andrew.taskpersonnel.ui.view.specialty.BaseSpecialtyHolder;

public class SpecialtyAdapter extends BaseSpecialtyAdapter<BaseSpecialtyHolder> {

    @NonNull
    @Override
    public BaseSpecialtyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseSpecialtyHolder(inflate(parent, viewType));
    }
}