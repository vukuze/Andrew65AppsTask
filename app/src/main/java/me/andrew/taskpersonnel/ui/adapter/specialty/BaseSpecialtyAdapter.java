package me.andrew.taskpersonnel.ui.adapter.specialty;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.andrew.taskpersonnel.R;
import me.andrew.taskpersonnel.data.Specialty;
import me.andrew.taskpersonnel.ui.view.specialty.BaseSpecialtyHolder;

/**
 * Базовый адаптер для отображения списка специальностей
 */
public abstract class BaseSpecialtyAdapter<T extends BaseSpecialtyHolder> extends RecyclerView.Adapter<T> {

    private List<Specialty> specialties = new ArrayList<>();

    public void replaceList(List<Specialty> specialties) {
        this.specialties = specialties;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull BaseSpecialtyHolder holder, int position) {
        Specialty specialty = specialties.get(position);
        holder.bindSpecialty(specialty);
    }

    @Override
    public int getItemCount() {
        return specialties.size();
    }

    protected View inflate(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(R.layout.list_item_specialty, parent, false);
    }
}