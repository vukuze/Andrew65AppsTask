package com.andrew.personnel.ui.adapter.specialty;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andrew.personnel.R;
import com.andrew.personnel.data.Specialty;
import com.andrew.personnel.ui.view.specialty.BaseSpecialtyHolder;

import java.util.List;

/**
 * Базовый адаптер для отображения списка специальностей
 */
public abstract class BaseSpecialtyAdapter<T extends BaseSpecialtyHolder> extends RecyclerView.Adapter<T> {

    private List<Specialty> specialties;

    public BaseSpecialtyAdapter(List<Specialty> specialties) {
        this.specialties = specialties;
    }

    @Override
    public void onBindViewHolder(BaseSpecialtyHolder holder, int position) {
        Specialty specialty = specialties.get(position);
        holder.bindSpecialty(specialty);
    }

    @Override
    public int getItemCount() {
        return specialties.size();
    }

    protected View inflate(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(R.layout.list_item_specialty, parent, false);
    }
}