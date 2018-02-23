package com.andrew.personnel.domain;

import android.util.Log;

import com.andrew.personnel.data.Repository;
import com.andrew.personnel.data.Specialty;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

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
    }

    public static final class RequestValues implements UseCase.RequestValues {
    }
}
