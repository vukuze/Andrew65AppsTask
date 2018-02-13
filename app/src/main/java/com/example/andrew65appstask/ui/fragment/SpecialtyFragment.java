package com.example.andrew65appstask.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.example.andrew65appstask.ui.adapter.specialty.SpecialtyAdapter;

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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");

        return inflater.inflate(R.layout.fragment_specialty, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);

        specialtiesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        specialtiesRecyclerView.setAdapter(new SpecialtyAdapter(new ArrayList<>()));

        if (specialtyPresenter.isRequestNeeded())
            specialtyPresenter.request();
    }

    /**
     * Обновляет данные списка специальностей, полученные из презентера
     */
    @Override
    public void updateItems(List<Specialty> specialties) {
        Log.d(TAG, "updateItems");

        specialtiesRecyclerView.setAdapter(new SpecialtyAdapter(specialties));
    }

    @Override
    public void onBackPressed() {
        specialtyPresenter.onBackCommandClick();
    }
}
