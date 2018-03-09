package me.andrew.taskpersonnel.presentation.presenter;

import android.util.Log;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import me.andrew.taskpersonnel.di.Injector;
import ru.terrakok.cicerone.Router;

public abstract class BasePresenter<View extends MvpView> extends MvpPresenter<View> implements Injector {

    @Inject
    public Router router;

    private Disposable requestDisposable = null;

    public BasePresenter() {
        super();

        Log.d(this.getClass().getSimpleName(), "constructor");

        inject();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        Log.d(this.getClass().getSimpleName(), "onFirstViewAttach");
    }

    protected void setRequestDisposable(Disposable disposable) {
        disposeChain(requestDisposable);
        this.requestDisposable = disposable;
    }

    public void onBackCommandClick() {
        Log.d(this.getClass().getSimpleName(), "onBackCommandClick");
        disposeChain(requestDisposable);
    }

    protected void disposeChain(Disposable disposable) {
        if (existSubscription(disposable)) {
            disposable.dispose();
            Log.d(this.getClass().getSimpleName(), "disposeChain disposable.dispose()");
        }
    }

    private boolean existSubscription(Disposable disposable) {
        return (disposable != null && !disposable.isDisposed());
    }
}
