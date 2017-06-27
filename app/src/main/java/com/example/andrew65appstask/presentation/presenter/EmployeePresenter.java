package com.example.andrew65appstask.presentation.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.andrew65appstask.App;
import com.example.andrew65appstask.employee.AbstractEmployee;
import com.example.andrew65appstask.employee.Employee;
import com.example.andrew65appstask.presentation.view.EmployeeView;
import com.example.andrew65appstask.specialty.Specialty;

import java.util.ArrayList;
import java.util.Collections;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.requery.Persistable;
import io.requery.reactivex.ReactiveEntityStore;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class EmployeePresenter extends MvpPresenter<EmployeeView> {

    private static final String TAG = "EmployeePresenter";

    protected int specialtyId;

    @Inject
    Router router;

    @Inject
    ReactiveEntityStore<Persistable> db;

//    private Single<ArrayList<Employee>> chain;
    private Disposable disposable = null;

    public EmployeePresenter() {
        Log.d(TAG, "constructor");

        App.getEmployeeComponent().inject(this);
    }

    public void request(int specialtyId) {
        this.specialtyId = specialtyId;

        disposable = db.select(Specialty.class)
                .where(Specialty.ID.eq(specialtyId))
                .get()
                .flowable()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .firstOrError()
                .map(specialty -> {
                    ArrayList<Employee> employees = new ArrayList<>();
                    for (AbstractEmployee abstractEmployee : specialty.getEmployees()) {
                        employees.add((Employee) abstractEmployee);
                    }
                    Collections.sort(employees, (o1, o2) -> o1.getLName().compareTo(o2.getLName()));
                    return employees;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(employees -> getViewState().updateItems(employees));
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
