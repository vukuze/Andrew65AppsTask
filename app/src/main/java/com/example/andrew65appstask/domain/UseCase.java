package com.example.andrew65appstask.domain;

import android.util.Log;

import com.example.andrew65appstask.dagger.Injector;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.requery.Persistable;
import io.requery.reactivex.ReactiveEntityStore;

abstract class UseCase<R> implements Injector {

    @Inject
    ReactiveEntityStore<Persistable> db;

    private Disposable disposable = null;

    UseCase() {
        inject();
    }

    protected abstract R execute();

    private void disposeChain() {
        if (disposable != null && !disposable.isDisposed()) {
            // TODO: непонятно как остановить выполнение цепочки
            disposable.dispose();
            Log.d(this.getClass().getSimpleName(), "disposeChain disposed");
        }
    }
}

