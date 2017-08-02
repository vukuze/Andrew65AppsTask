package com.example.andrew65appstask.domain;

import android.util.Log;

import com.example.andrew65appstask.App;
import com.example.andrew65appstask.db.Employee;
import com.example.andrew65appstask.db.Specialty;
import com.example.andrew65appstask.network.EmployeeRestAnswer;
import com.example.andrew65appstask.network.SixtyFiveAppsRestService;
import com.example.andrew65appstask.util.JsonToEntityConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 *
 */

public class GetNetworkData extends UseCase<Single<Iterable<Employee>>> {

    private static final String TAG = "GetNetworkData";

    @Inject
    SixtyFiveAppsRestService service;

    @Override
    public Single<Iterable<Employee>> execute() {
        return Single.just(Observable.empty())
                .observeOn(Schedulers.io())
                // задержка для того, чтобы успеть увидеть исполнение операций
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
                .observeOn(Schedulers.computation())
                .delay(1, TimeUnit.SECONDS)
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
                .observeOn(Schedulers.io())
                // Обновляет данные в SQLite из REST ответа
                .flatMap(employees -> {
                    Log.d(TAG, "flatMap -> upsert");
                    return db.upsert(employees).toObservable().singleOrError();
                })
                .cache();
    }

    @Override
    public void inject() {
        App.getSplashComponent().inject(this);
    }
}
