package com.andrew.personnel.data;

import com.andrew.personnel.data.network.Response;
import com.andrew.personnel.data.network.SixtyFiveAppsRestService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import io.requery.Persistable;
import io.requery.reactivex.ReactiveEntityStore;

/**
 *
 */

public class Repository {

    private ReactiveEntityStore<Persistable> db;
    private SixtyFiveAppsRestService service;

    public Repository(ReactiveEntityStore<Persistable> db, SixtyFiveAppsRestService service) {
        this.db = db;
        this.service = service;
    }

    public Single<Response> getFile() {
        return service.getFile();
    }

    public void emptyDB() {
        db.delete(Specialty.class).get().value();
        db.delete(Employee.class).get().value();
    }

    public Single<Iterable<Employee>> upsert(List<Employee> employees) {
        return db.upsert(employees).toObservable().singleOrError();
    }

    public Single<List<Specialty>> getSpecialties() {
        return db.select(Specialty.class)
                .get()
                .observable()
                .toList();
    }

    public Single<List<Employee>> getEmployees(int specialtyId) {
        return db.select(Specialty.class)
                .where(Specialty.ID.eq(specialtyId))
                .get()
                .observable()
                .firstOrError()
                .observeOn(Schedulers.computation())
                .map(specialty -> {
                    ArrayList<Employee> employees = new ArrayList<>();
                    for (AbstractEmployee abstractEmployee : specialty.getEmployees()) {
                        employees.add((Employee) abstractEmployee);
                    }
                    return employees;
                })
                .map(employees -> {
                    Collections.sort(employees, (o1, o2) -> o1.getLName().compareTo(o2.getLName()));
                    return employees;
                });
    }

    public Single<Employee> getEmployeeDetails(int employeeId) {
        return db.select(Employee.class)
                .where(Employee.ID.eq(employeeId))
                .get()
                .observable()
                .firstOrError();
    }
}
