package com.example.andrew65appstask.presentation.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.andrew65appstask.App;
import com.example.andrew65appstask.data.Employee;
import com.example.andrew65appstask.presentation.view.DetailsView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.requery.Persistable;
import io.requery.reactivex.ReactiveEntityStore;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class DetailsPresenter extends MvpPresenter<DetailsView> {

    private static final String TAG = "DetailsPresenter";

    protected int employeeId;

    @Inject
    Router router;

    @Inject
    ReactiveEntityStore<Persistable> db;

    //    private Single<Employee> chain;
    private Disposable disposable = null;

    public DetailsPresenter() {
        Log.d(TAG, "constructor");

        App.getDetailsComponent().inject(this);
    }

    public void request(int employeeId) {
        Log.d(TAG, "request");
        this.employeeId = employeeId;

        db.select(Employee.class)
                .where(Employee.ID.eq(employeeId))
                .get()
                .flowable()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .firstOrError()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(employee -> getViewState().updateItems(employee));
    }

    public void onBackCommandClick() {
        Log.d(TAG, "onBackCommandClick");
        disposeChain();
        router.exit();
    }

    private void disposeChain() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
            Log.d(TAG, "disposeChain disposed");
        }
    }
}