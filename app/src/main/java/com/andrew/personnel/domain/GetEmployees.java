package com.andrew.personnel.domain;

import android.util.Log;

import com.andrew.personnel.data.Employee;
import com.andrew.personnel.data.Repository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * UseCase: получение списка Employees
 */

public class GetEmployees extends UseCase<GetEmployees.RequestValues, List<Employee>> {

    public GetEmployees(Repository repository) {
        super(repository);
    }

    @Override
    public Single<List<Employee>> executeUseCase(final RequestValues values) {
        Log.d(this.getClass().getSimpleName(), "executeUseCase");
        int specialtyId = values.getSpecialtyId();
        return Single.just(Observable.empty())
                .observeOn(Schedulers.computation())
                .flatMap(emptyObservable -> repository.getEmployees(specialtyId));
    }

    public static final class RequestValues implements UseCase.RequestValues {

        private final int specialtyId;

        public RequestValues(int specialtyId) {
            this.specialtyId = specialtyId;
        }

        int getSpecialtyId() {
            return specialtyId;
        }
    }
}
