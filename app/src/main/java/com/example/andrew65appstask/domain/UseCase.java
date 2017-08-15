package com.example.andrew65appstask.domain;

import com.example.andrew65appstask.data.Repository;

import io.reactivex.Single;

public abstract class UseCase<Param extends UseCase.RequestValues, Result> {

    protected Repository repository;

    private Param requestValues;

    UseCase(Repository repository) {
        this.repository = repository;
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
    public interface RequestValues {
    }
}

