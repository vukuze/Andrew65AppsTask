package com.example.andrew65appstask.presentation.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.example.andrew65appstask.App;
import com.example.andrew65appstask.db.Employee;
import com.example.andrew65appstask.domain.GetNetworkData;
import com.example.andrew65appstask.navigation.Screens;
import com.example.andrew65appstask.presentation.view.SplashView;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class SplashPresenter extends BasePresenter<SplashView> {

//    private static final String TAG = "SplashPresenter";

    @Inject
    GetNetworkData networkData;

    @Override
    public void inject() {
        App.getSplashComponent().inject(this);
    }

    public void request() {
        disposeChain();

        Single<Iterable<Employee>> chain = networkData.execute();
        chain.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        employees -> router.replaceScreen(Screens.SPECIALTY_FRAGMENT),
                        throwable -> getViewState().handleErrors(throwable));
    }

    @Override
    public void onBackCommandClick() {
        super.onBackCommandClick();
        router.exit();
    }
}
