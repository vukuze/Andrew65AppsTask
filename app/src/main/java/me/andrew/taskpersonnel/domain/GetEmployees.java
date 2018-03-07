package me.andrew.taskpersonnel.domain;

import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import me.andrew.taskpersonnel.data.Employee;
import me.andrew.taskpersonnel.data.Repository;

/**
 * UseCase: получение списка Employees
 */

public class GetEmployees extends UseCase<GetEmployees.RequestValues, List<Employee>> {

    public GetEmployees(Repository repository) {
        super(repository);
    }

    @Override
    public Single<List<Employee>> executeUseCase(final RequestValues values) {
        final int specialtyId = values.getSpecialtyId();
        final int limit = values.getLimit();
        final int offset = values.getOffset();

        Log.d(this.getClass().getSimpleName(), "executeUseCase, limit: "+limit+", offset: "+offset);

        return Single.just(Observable.empty())
                .observeOn(Schedulers.computation())
                .flatMap(emptyObservable -> repository.getEmployees(specialtyId, limit, offset));
    }

    public static final class RequestValues implements UseCase.RequestValues {

        private final int specialtyId;
        private final int limit;
        private final int offset;

        public RequestValues(int specialtyId, int limit, int offset) {
            this.specialtyId = specialtyId;
            this.limit = limit;
            this.offset = offset;
        }

        int getLimit() {
            return limit;
        }

        int getOffset() {
            return offset;
        }

        int getSpecialtyId() {
            return specialtyId;
        }
    }
}
