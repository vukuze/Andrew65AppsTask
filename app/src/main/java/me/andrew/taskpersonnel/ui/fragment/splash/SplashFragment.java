package me.andrew.taskpersonnel.ui.fragment.splash;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
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

import butterknife.BindView;
import butterknife.OnClick;
import me.andrew.taskpersonnel.App;
import me.andrew.taskpersonnel.R;
import me.andrew.taskpersonnel.presentation.presenter.splash.SplashPresenter;
import me.andrew.taskpersonnel.presentation.view.splash.SplashView;
import me.andrew.taskpersonnel.ui.fragment.BaseFragment;

public class SplashFragment extends BaseFragment implements SplashView {

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

    @Override
    public void inject() {
        App.getSplashComponent().inject(this);
    }

    @StringRes
    @Override
    public int changeActionBarTitle() {
        return R.string.fragment_splash_name;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button.setText(LOADING_ERROR_BTN_TEXT);
    }

    @OnClick(R.id.splash_button)
    public void startRequest() {
        splashPresenter.request();
    }

    public void setViewLoadingState(boolean isVisible) {
        Log.d(TAG, "setViewLoadingState: " + isVisible);

        progressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        imageView.setVisibility(isVisible ? View.GONE : View.VISIBLE);
        textView.setText(isVisible ? LOADING_NOW : LOADING_ERROR_FINISH);
        button.setVisibility(isVisible ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        splashPresenter.onBackCommandClick();
    }
}