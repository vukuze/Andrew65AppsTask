package com.example.andrew65appstask.ui.adapter.specialty;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.andrew65appstask.R;
import com.example.andrew65appstask.data.Specialty;

import butterknife.BindView;
import butterknife.ButterKnife;

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