package me.andrew.taskpersonnel.ui.adapter.specialty;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.andrew.taskpersonnel.R;
import me.andrew.taskpersonnel.data.Specialty;

/**
 * Базовый адаптер для отображения списка специальностей
 */
public abstract class BaseSpecialtyAdapter<T extends BaseSpecialtyAdapter.BaseSpecialtyHolder> extends RecyclerView.Adapter<T> {

    private List<Specialty> specialties = new ArrayList<>();

    public void replaceList(List<Specialty> specialties) {
        this.specialties = specialties;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull BaseSpecialtyAdapter.BaseSpecialtyHolder holder, int position) {
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

    /**
     * Базовый Holder для отображения специальности (без реализации OnClick)
     */
    public class BaseSpecialtyHolder extends RecyclerView.ViewHolder {

        protected Specialty specialty;

        @BindView(R.id.item_specialty_text_view)
        TextView nameTextView;

        public BaseSpecialtyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindSpecialty(Specialty specialty) {
            this.specialty = specialty;
            nameTextView.setText(this.specialty.getName());
        }
    }
}