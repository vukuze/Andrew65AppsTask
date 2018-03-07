package me.andrew.taskpersonnel.domain;

import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import me.andrew.taskpersonnel.data.Repository;
import me.andrew.taskpersonnel.data.Specialty;

/**
 * UseCase: получение списка Specialties
 */

public class GetSpecialties extends UseCase<GetSpecialties.RequestValues, List<Specialty>> {

    public GetSpecialties(Repository repository) {
        super(repository);
    }

    @Override
    public Single<List<Specialty>> executeUseCase(final RequestValues values) {
        Log.d(this.getClass().getSimpleName(), "executeUseCase");
        return Single.just(Observable.empty())
                .observeOn(Schedulers.computation())
                .flatMap(emptyObservable -> repository.getSpecialties());
//        .flatMap(emptyObservable -> repository.getEmployees(specialtyId));
    }

    public static final class RequestValues implements UseCase.RequestValues {
//        private final int specialtyId;
//
//        public RequestValues(int specialtyId) {
//            this.specialtyId = specialtyId;
//        }
//
//        int getSpecialtyId() {
//            return specialtyId;
//        }
    }
}
