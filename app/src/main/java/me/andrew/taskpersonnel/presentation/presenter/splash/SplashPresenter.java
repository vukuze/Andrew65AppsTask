package me.andrew.taskpersonnel.presentation.presenter.splash;

import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.andrew.taskpersonnel.App;
import me.andrew.taskpersonnel.domain.UpdateData;
import me.andrew.taskpersonnel.navigation.Screens;
import me.andrew.taskpersonnel.presentation.presenter.BasePresenter;
import me.andrew.taskpersonnel.presentation.view.splash.SplashView;

@InjectViewState
public class SplashPresenter extends BasePresenter<SplashView> {

    @Inject
    UpdateData networkData;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        request();
    }

    @Override
    public void inject() {
        App.getSplashComponent().inject(this);
    }

    public void request() {
        getViewState().setViewLoadingState(true);
        setRequestDisposable(networkData
                .executeUseCase(new UpdateData.RequestValues())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        employees -> router.replaceScreen(Screens.SPECIALTY_ACTIVITY),
                        throwable -> getViewState().setViewLoadingState(false)));
    }
}
