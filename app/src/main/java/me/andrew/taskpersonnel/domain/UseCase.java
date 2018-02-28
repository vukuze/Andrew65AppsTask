package me.andrew.taskpersonnel.domain;

import android.util.Log;

import me.andrew.taskpersonnel.data.Repository;

import io.reactivex.Single;

public abstract class UseCase<Param extends UseCase.RequestValues, Result> {

    protected Repository repository;

    UseCase(Repository repository) {
        this.repository = repository;
        Log.d(this.getClass().getSimpleName(), "constructor");
    }

    public abstract Single<Result> executeUseCase(Param requestValues);

    /**
     * Входные данные для UseCase
     */
    interface RequestValues {
    }
}

