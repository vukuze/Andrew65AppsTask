package com.example.andrew65appstask.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.andrew65appstask.App;
import com.example.andrew65appstask.R;
import com.example.andrew65appstask.data.Specialty;
import com.example.andrew65appstask.presentation.presenter.SpecialtyPresenter;
import com.example.andrew65appstask.presentation.view.SpecialtyView;
import com.example.andrew65appstask.ui.BackButtonListener;
import com.example.andrew65appstask.ui.BaseSpecialtyAdapter;
import com.example.andrew65appstask.ui.BaseSpecialtyHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SpecialtyFragment extends BaseFragment implements SpecialtyView, BackButtonListener {

    public static final String TAG = "SpecialtyFragment";

    @InjectPresenter
    SpecialtyPresenter specialtyPresenter;

    @BindView(R.id.fragment_specialty_recycler_view)
    RecyclerView specialtiesRecyclerView;

    public static Fragment newInstance() {
        return new SpecialtyFragment();
    }

    @Override
    public void inject() {
        App.getSpecialtyComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");

        return inflater.inflate(R.layout.fragment_specialty, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);

        specialtiesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        specialtiesRecyclerView.setAdapter(new SpecialtyAdapter(new ArrayList<>()));

        specialtyPresenter.request();
    }

    /*
    * Обновляет данные списка специальностей, полученные из презентера
    */
    @Override
    public void updateItems(List<Specialty> specialties) {
        Log.d(TAG, "updateItems");

        specialtiesRecyclerView.setAdapter(new SpecialtyAdapter(specialties));
    }

    @Override
    public boolean onBackPressed() {
        Log.d(TAG, "onBackPressed");
        specialtyPresenter.onBackCommandClick();
        return true;
    }

    private class SpecialtyHolder extends BaseSpecialtyHolder implements View.OnClickListener {

        SpecialtyHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            specialtyPresenter.onClick(specialty.getId());
        }
    }

    private class SpecialtyAdapter extends BaseSpecialtyAdapter<SpecialtyHolder> {

        SpecialtyAdapter(List<Specialty> specialties) {
            super(specialties);
        }

        @Override
        public SpecialtyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new SpecialtyHolder(inflate(parent, viewType));
        }
    }
}
