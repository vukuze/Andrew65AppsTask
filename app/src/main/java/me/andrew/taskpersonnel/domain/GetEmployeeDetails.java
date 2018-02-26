package me.andrew.taskpersonnel.domain;

import android.util.Log;

import me.andrew.taskpersonnel.data.Employee;
import me.andrew.taskpersonnel.data.Repository;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * UseCase: получение информации о Employee
 */

public class GetEmployeeDetails extends UseCase<GetEmployeeDetails.RequestValues, Employee> {

    public GetEmployeeDetails(Repository repository) {
        super(repository);
    }

    @Override
    public Single<Employee> executeUseCase(final RequestValues values) {
        Log.d(this.getClass().getSimpleName(), "executeUseCase");
        int employeeId = values.getEmployeeId();
        return Single.just(Observable.empty())
                .observeOn(Schedulers.computation())
                .flatMap(emptyObservable -> repository.getEmployeeDetails(employeeId));
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private final int employeeId;

        public RequestValues(int employeeId) {
            this.employeeId = employeeId;
        }

        public int getEmployeeId() {
            return employeeId;
        }
    }
}
