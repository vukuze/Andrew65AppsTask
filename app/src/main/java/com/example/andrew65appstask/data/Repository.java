package com.example.andrew65appstask.data;

import com.example.andrew65appstask.data.network.Response;
import com.example.andrew65appstask.data.network.SixtyFiveAppsRestService;

import java.util.List;

import io.reactivex.Single;
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
}
