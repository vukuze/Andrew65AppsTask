package com.example.andrew65appstask.specialty;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.andrew65appstask.R;
import com.example.andrew65appstask.specialty.Specialty;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Базовый Holder для отображения специальности (без реализации OnClick)
 */
public class BaseSpecialtyHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_specialty_text_view)
    TextView nameTextView;

    protected Specialty specialty;

    public BaseSpecialtyHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void bindSpecialty(Specialty specialty) {
        this.specialty = specialty;
        nameTextView.setText(this.specialty.getName());
    }
}