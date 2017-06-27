//package com.example.andrew65appstask.specialty;
//
//import android.os.Bundle;
//import android.util.Log;
//
//import com.example.andrew65appstask.unused.BasePresenter;
//
//import javax.inject.Inject;
//
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.schedulers.Schedulers;
//import io.requery.Persistable;
//import io.requery.reactivex.ReactiveEntityStore;
//
//public class SpecialtyPresenter extends BasePresenter<SpecialtyFragment> {
//
//    private static final String TAG = "SpecialtyPresenter";
//    private static final int RESTARTABLE_SPECIALTIES = 1;
//
//    @Inject
//    ReactiveEntityStore<Persistable> db;
//
//    @Override
//    protected void onCreate(Bundle savedState) {
//        Log.d(TAG, "onCreate");
//        super.onCreate(savedState);
//
//        restartableLatestCache(RESTARTABLE_SPECIALTIES,
//                () -> db.select(Specialty.class)
//                        .get()
//                        .observable()
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(Schedulers.io())
//                        .toList()
//                        .toObservable()
//                        .observeOn(AndroidSchedulers.mainThread()),
//                SpecialtyFragment::updateItems
//        );
//    }
//
//    void request(){
//        start(RESTARTABLE_SPECIALTIES);
//    }
//}