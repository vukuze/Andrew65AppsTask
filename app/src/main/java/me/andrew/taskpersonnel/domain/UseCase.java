package me.andrew.taskpersonnel.domain;

import android.util.Log;

import me.andrew.taskpersonnel.data.Repository;

import io.reactivex.Single;

public abstract class UseCase<Param extends UseCase.RequestValues, Result> {

    protected Repository repository;

    private Param requestValues;

    UseCase(Repository repository) {
        this.repository = repository;
        Log.d(this.getClass().getSimpleName(), "constructor");
    }

    public Param getRequestValues() {
        return requestValues;
    }

    public void setRequestValues(Param requestValues) {
        this.requestValues = requestValues;
    }

    public abstract Single<Result> executeUseCase(Param requestValues);

//    public abstract Single<Param> execute();

    /**
     * Входные данные для UseCase
     */
    interface RequestValues {
    }
}

