package com.example.andrew65appstask.ui.view.employee;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.andrew65appstask.R;

/**
 * Класс представления двух связанных TextView, заголовка и подставляемого значения,
 * например "Фамилия" : "Иванов"
 */

public class DoubleTextView extends LinearLayout {

    private TextView textView;

    public DoubleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.DoubleTextView, 0, 0);

        try {
            String service = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(service);
            LinearLayout layout = (LinearLayout) li.inflate(R.layout.double_text_view, this, true);

            String header = a.getString(R.styleable.DoubleTextView_header);
            header = header == null ? "" : header;

            String text = a.getString(R.styleable.DoubleTextView_text);
            text = text == null ? "" : text;

            TextView headerView = layout.findViewById(R.id.double_text_header_view);
            textView = layout.findViewById(R.id.double_text_text_view);

            headerView.setText(header);
            textView.setText(text);
        } finally {
            a.recycle();
        }
    }

    public void setText(String text) {
        textView.setText(text);
        invalidate();
        requestLayout();
    }
}
