package com.example.andrew65appstask.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.andrew65appstask.App;
import com.example.andrew65appstask.R;
import com.example.andrew65appstask.presentation.presenter.SplashPresenter;
import com.example.andrew65appstask.presentation.view.SplashView;
import com.example.andrew65appstask.ui.BackButtonListener;

import butterknife.BindView;
import butterknife.OnClick;
import ru.terrakok.cicerone.Navigator;

public class SplashFragment extends BaseFragment//BaseFragmentWithNavigator
        implements SplashView, BackButtonListener {

    private static final String TAG = "SplashFragment";
    private static final String LOADING_NOW = "Загрузка данных с сервера ...";
    private static final String LOADING_ERROR_FINISH = "Произошла ошибка при загрузке";
    private static final String LOADING_ERROR_BTN_TEXT = "Повторить";

    @BindView(R.id.splash_text_view)
    TextView textView;

    @BindView(R.id.splash_button)
    Button button;

    @BindView(R.id.splash_error_loading)
    ImageView imageView;

    @BindView(R.id.splash_progress_bar)
    ContentLoadingProgressBar progressBar;

    @InjectPresenter
    SplashPresenter splashPresenter;

    public static Fragment newInstance() {
        return new SplashFragment();
    }

//    @Override
//    protected Navigator createNavigator() {
//        return null;
//    }

    @Override
    public void inject() {
        App.getSplashComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");

        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated");

        super.onViewCreated(view, savedInstanceState);
        button.setText(LOADING_ERROR_BTN_TEXT);

        startRequest();
    }

    @OnClick(R.id.splash_button)
    public void startRequest() {
        Log.d(TAG, "startRequest");

        setViewVisibility(true);

        splashPresenter.request();
    }

    private void setViewVisibility(boolean isVisible) {
        progressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        imageView.setVisibility(isVisible ? View.GONE : View.VISIBLE);
        textView.setText(isVisible ? LOADING_NOW : LOADING_ERROR_FINISH);
        button.setVisibility(isVisible ? View.GONE : View.VISIBLE);
    }

    /*
    * Действия, выполняемые в случае возникновения ошибок сетевых запросов и запросов к БД
    */
    public void handleErrors(Throwable throwable) {
        Log.d(TAG, "handleErrors: " + throwable.getMessage());

        setViewVisibility(false);
    }

    @Override
    public boolean onBackPressed() {
        Log.d(TAG, "onBackPressed");
        splashPresenter.onBackCommandClick();
        return true;
    }
}
