package com.example.andrew65appstask.presentation.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.example.andrew65appstask.App;
import com.example.andrew65appstask.presentation.view.SpecialtyView;
import com.example.andrew65appstask.specialty.Specialty;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class SpecialtyPresenter extends BasePresenter<SpecialtyView> {

    private static final String TAG = "SpecialtyPresenter";

    private Single<List<Specialty>> chain;
//    private Disposable disposable = null;

    public SpecialtyPresenter() {
        Log.d(TAG, "constructor");

        chain = db.select(Specialty.class)
                .get()
                .flowable()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .toList()
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void inject() {
        App.getSpecialtyComponent().inject(this);
    }

    public void request() {
        disposeChain();
        chain.subscribe(specialties -> getViewState().updateItems(specialties));
    }

    @Override
    public void onBackCommandClick() {
//        Log.d(TAG, "onBackCommandClick");
//        disposeChain();
        super.onBackCommandClick();
        router.exit();
    }

//    private void disposeChain() {
//        if (disposable != null && !disposable.isDisposed()) {
//            disposable.dispose();
//            Log.d(TAG, "disposeChain disposed");
//        }
//    }
}