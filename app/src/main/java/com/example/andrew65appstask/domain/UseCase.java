package com.example.andrew65appstask.domain;

import com.example.andrew65appstask.data.Repository;

abstract class UseCase<R> {

    protected Repository repository;

    UseCase(Repository repository) {
        this.repository = repository;
    }

    protected abstract R execute();
}

