package com.example.andrew65appstask.ui.adapter.specialty;

import android.view.ViewGroup;

import com.example.andrew65appstask.data.Specialty;

import java.util.List;

/**
 * SpecialtyAdapter с реализацией View.OnClickListener в SpecialtyHolder
 */

public class SpecialtyAdapter extends BaseSpecialtyAdapter<SpecialtyHolder> {

    public SpecialtyAdapter(List<Specialty> specialties) {
        super(specialties);
    }

    @Override
    public SpecialtyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SpecialtyHolder(inflate(parent, viewType));
    }
}
