//package com.example.andrew65appstask.specialty;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.andrew65appstask.R;
//import com.example.andrew65appstask.base.BaseFragment;
//import com.example.andrew65appstask.ui.activity.EmployeeActivity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//import nucleus5.factory.RequiresPresenter;
//
//@RequiresPresenter(SpecialtyPresenter.class)
//public class SpecialtyFragment extends BaseFragment<SpecialtyPresenter> {
//
//    private static final int REQUEST_SPECIALTY = 1;
//    private static final String TAG = "SpecialtyFragment";
//
//    @BindView(R.id.fragment_specialty_recycler_view)
//    RecyclerView specialtiesRecyclerView;
//
//    public static SpecialtyFragment newInstance() {
//        return new SpecialtyFragment();
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        Log.d(TAG, "onCreateView");
//
//        View view = inflater.inflate(R.layout.fragment_specialty, container, false);
//        return view;
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        Log.d(TAG, "onViewCreated");
//        super.onViewCreated(view, savedInstanceState);
//
//        specialtiesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        specialtiesRecyclerView.setAdapter(new SpecialtyAdapter(new ArrayList<>()));
//
//        getPresenter().request();
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_SPECIALTY) {
//
//        }
//    }
//
//    /*
//    * Обновляет данные списка специальностей, полученные из презентера
//    */
//    public void updateItems(List<Specialty> specialties) {
//        Log.d(TAG, "updateItems");
//
//        specialtiesRecyclerView.setAdapter(new SpecialtyAdapter(specialties));
//    }
//
//    private class SpecialtyHolder extends BaseSpecialtyHolder implements View.OnClickListener {
//
//        SpecialtyHolder(View itemView) {
//            super(itemView);
//            itemView.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View v) {
//            Context context = v.getContext();
//            Intent intent = EmployeeActivity.newIntent(context, specialty.getId());
//            startActivityForResult(intent, REQUEST_SPECIALTY);
//        }
//    }
//
//    private class SpecialtyAdapter extends BaseSpecialtyAdapter<SpecialtyHolder> {
//
//        public SpecialtyAdapter(List<Specialty> specialties) {
//            super(specialties);
//        }
//
//        @Override
//        public SpecialtyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            return new SpecialtyHolder(inflate(parent, viewType));
//        }
//
//        public void updateAdapter(int specialtyId){
//            // TODO: 10.04.2017 update recyclerview
//        }
//    }
//}
