package com.example.andrew65appstask.domain;

import android.util.Log;

import com.example.andrew65appstask.data.Employee;
import com.example.andrew65appstask.data.Repository;
import com.example.andrew65appstask.data.network.EmployeeRestAnswer;
import com.example.andrew65appstask.data.network.Response;
import com.example.andrew65appstask.util.JsonToEntityConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * UseCase
 */

public class UpdateData extends UseCase<Single<Iterable<Employee>>> {

    public UpdateData(Repository repository) {
        super(repository);
    }

    @Override
    public Single<Iterable<Employee>> execute() {
        return Single.just(Observable.empty())
                // delay используется, чтобы успеть увидеть выполнение операций
                .delay(1, TimeUnit.SECONDS)
                .flatMap(emptyObservable -> getNetworkData())
                .delay(1, TimeUnit.SECONDS)
                .map(response -> {
                    emptyDb();
                    return response;
                })
                .delay(1, TimeUnit.SECONDS)
                .observeOn(Schedulers.computation())
                .map(this::convertRestToDb)
                .delay(1, TimeUnit.SECONDS)
                .flatMap(this::upsertDb)
                .cache();
    }

    // Подготовить REST данные к записи в БД, конвертируя формат ответа в формат сущности БД
    private List<Employee> convertRestToDb(Response response) {
        Log.d(this.getClass().getSimpleName(), "map -> convert REST to DB");
        List<Employee> employees = new ArrayList<>();
        for (EmployeeRestAnswer employeeRestAnswer : response.getEmployees()) {
            Employee employee = JsonToEntityConverter.convertEmployee(employeeRestAnswer);
            employees.add(employee);
        }
        return employees;
    }

    // запрос к серверу и получение REST ответа
    private Single<Response> getNetworkData() {
        return Single.just(Observable.empty())
                .observeOn(Schedulers.io())
                .flatMap(unused -> repository.getFile());
    }

    // Удалить все данные из БД
    private void emptyDb() {
        repository.emptyDB();
    }

    // Обновляет данные в SQLite из REST ответа
    private Single<Iterable<Employee>> upsertDb(List<Employee> employees) {
        return Single.just(employees)
                .observeOn(Schedulers.io())
                // запрос к серверу и получение REST ответа
                .flatMap(employeeList -> repository.upsert(employeeList));
    }
}
