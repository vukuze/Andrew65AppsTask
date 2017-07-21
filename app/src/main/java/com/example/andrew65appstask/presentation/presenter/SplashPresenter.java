package com.example.andrew65appstask.presentation.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.example.andrew65appstask.App;
import com.example.andrew65appstask.cicerone.Screens;
import com.example.andrew65appstask.util.JsonToEntityConverter;
import com.example.andrew65appstask.db.Employee;
import com.example.andrew65appstask.network.EmployeeRestAnswer;
import com.example.andrew65appstask.network.SixtyFiveAppsRestService;
import com.example.andrew65appstask.presentation.view.SplashView;
import com.example.andrew65appstask.db.Specialty;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class SplashPresenter extends BasePresenter<SplashView> {

    private static final String TAG = "SplashPresenter";

    @Inject
    SixtyFiveAppsRestService service;

    private Single<Iterable<Employee>> chain;
//    private Disposable disposable = null;

    public SplashPresenter() {
        Log.d(TAG, "constructor");

        // Инициация начинается с Single.empty(), а не с service.getFile(),
        // т.к. последний блокирует UI и не отображает ProgressBar
        chain = Single.just(Observable.empty())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                // задержка, чтобы увидеть прогрессбар
                .delay(1, TimeUnit.SECONDS)
                // запрос к серверу и получение REST ответа
                .flatMap(emptyObservable -> {
                    Log.d(TAG, "flatMap -> getFile");
                    return service.getFile();
                })
                .delay(1, TimeUnit.SECONDS)
                // Удалить все данные из БД
                .map(response -> {
                    Log.d(TAG, "map -> delete");
                    db.delete(Specialty.class).get().value();
                    db.delete(Employee.class).get().value();
                    return response;
                })
                .delay(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.computation())
                // Подготовить REST данные к записи в БД, конвертируя формат ответа в формат сущности БД
                .map(response -> {
                    Log.d(TAG, "map -> employees");
                    List<Employee> employees = new ArrayList<>();
                    for (EmployeeRestAnswer employeeRestAnswer : response.getEmployees()) {
                        Employee employee = JsonToEntityConverter.convertEmployee(employeeRestAnswer);
                        employees.add(employee);
                    }
                    return employees;
                })
                .delay(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                // Обновляет данные в SQLite из REST ответа
                .flatMap(employees -> {
                    Log.d(TAG, "flatMap -> upsert");
                    return db.upsert(employees).toObservable().singleOrError();
                })
                .cache()
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void inject() {
        App.getSplashComponent().inject(this);
    }

    public void request() {
        disposeChain();
        chain.subscribe(
                employees -> router.replaceScreen(Screens.SPECIALTY_FRAGMENT),
                throwable -> getViewState().handleErrors(throwable));
    }

    @Override
    public void onBackCommandClick() {
        super.onBackCommandClick();
        router.exit();
    }
}
