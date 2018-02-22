package com.example.andrew65appstask.presentation.presenter;

import android.util.Log;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;
import com.example.andrew65appstask.di.Injector;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import ru.terrakok.cicerone.Router;

public abstract class BasePresenter<View extends MvpView> extends MvpPresenter<View>
        implements Injector {

    @Inject
    public Router router;

    private Disposable disposable = null;

    public BasePresenter() {
        super();

        inject();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        Log.d(this.getClass().getSimpleName(), "onFirstViewAttach");
    }

    protected void setDisposable(Disposable disposable) {
        disposeChain();
        this.disposable = disposable;
    }

    public void onBackCommandClick() {
        Log.d(this.getClass().getSimpleName(), "onBackCommandClick");
        disposeChain();
    }

    private void disposeChain() {
        if (disposable != null && !disposable.isDisposed()) {
            // TODO: непонятно как остановить выполнение цепочки
            disposable.dispose();
            Log.d(this.getClass().getSimpleName(), "disposeChain disposable.dispose()");
        }
    }
}
