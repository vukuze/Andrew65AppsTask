package me.andrew.taskpersonnel.data;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import io.requery.Persistable;
import io.requery.reactivex.ReactiveEntityStore;

public class Repository {

    private ReactiveEntityStore<Persistable> db;

    public Repository(ReactiveEntityStore<Persistable> db) {
        this.db = db;
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

    public Single<List<Employee>> getEmployees(int specialtyId, int limit, int offset) {
        return db.select(Employee.class)
                .join(Specialty_Employee.class).on(Employee.ID.equal(Specialty_Employee.EMPLOYEE_ID))
                .join(Specialty.class).on(Specialty_Employee.SPECIALTY_ID.equal(Specialty.ID))
                .where(Specialty.ID.equal(specialtyId))
                .orderBy(Employee.L_NAME.asc())
                .limit(limit)
                .offset(offset)
                .get()
                .observable()
                .observeOn(Schedulers.computation())
                .toList();
    }

    public Single<Employee> getEmployeeDetails(int employeeId) {
        return db.select(Employee.class)
                .where(Employee.ID.eq(employeeId))
                .get()
                .observable()
                .firstOrError();
    }
}
