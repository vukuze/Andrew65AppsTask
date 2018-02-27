package me.andrew.taskpersonnel.ui.fragment.specialty;

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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.andrew.taskpersonnel.App;
import me.andrew.taskpersonnel.R;
import me.andrew.taskpersonnel.data.Specialty;
import me.andrew.taskpersonnel.presentation.presenter.specialty.SpecialtyPresenter;
import me.andrew.taskpersonnel.presentation.view.specialty.SpecialtyView;
import me.andrew.taskpersonnel.ui.adapter.specialty.BaseSpecialtyAdapter;
import me.andrew.taskpersonnel.ui.fragment.BaseFragment;
import me.andrew.taskpersonnel.ui.view.specialty.BaseSpecialtyHolder;

public class SpecialtyFragment extends BaseFragment implements SpecialtyView {

    public static final String TAG = "SpecialtyFragment";
    public static int SPECIALTY_NOT_DEFINED = -1;

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

    @Override
    public int setActionBarTitle() {
        return R.string.fragment_specialty_name;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_specialty, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        specialtiesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        specialtiesRecyclerView.setAdapter(new SpecialtyAdapter(new ArrayList<>()));
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

    /**
     * SpecialtyAdapter с реализацией View.OnClickListener в SpecialtyHolder
     */
    private class SpecialtyAdapter extends BaseSpecialtyAdapter<SpecialtyHolder> {

        SpecialtyAdapter(List<Specialty> specialties) {
            super(specialties);
        }

        @Override
        public SpecialtyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new SpecialtyHolder(inflate(parent, viewType));
        }
    }

    /**
     * SpecialtyHolder, в котором implements View.OnClickListener
     */
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
}
