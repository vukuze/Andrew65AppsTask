package me.andrew.taskpersonnel.ui.view.specialty;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.andrew.taskpersonnel.R;
import me.andrew.taskpersonnel.data.Specialty;

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

    public void bindSpecialty(Specialty specialty) {
        this.specialty = specialty;
        nameTextView.setText(this.specialty.getName());
    }
}