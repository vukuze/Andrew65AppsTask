package me.andrew.taskpersonnel.ui.activity.employee;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import me.andrew.taskpersonnel.App;
import me.andrew.taskpersonnel.R;
import me.andrew.taskpersonnel.navigation.EmployeeActivityNavigator;
import me.andrew.taskpersonnel.presentation.presenter.employee.EmployeeActivityPresenter;
import me.andrew.taskpersonnel.presentation.view.employee.EmployeeActivityView;
import me.andrew.taskpersonnel.ui.activity.BaseActivity;
import me.andrew.taskpersonnel.ui.fragment.employee.DetailsFragment;
import ru.terrakok.cicerone.Navigator;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;
import static me.andrew.taskpersonnel.ui.fragment.specialty.SpecialtyFragment.SPECIALTY_NOT_DEFINED;

public class EmployeeActivity extends BaseActivity implements EmployeeActivityView {

    private static final String TAG = "EmployeeActivity";
    private static final String EXTRA_SPECIALTY_ID = "specialty_id";

    @InjectPresenter
    EmployeeActivityPresenter employeeActivityPresenter;

    public static Intent newIntent(Context context, int specialtyId) {
        Intent intent = new Intent(context, EmployeeActivity.class);
        intent.putExtra(EXTRA_SPECIALTY_ID, specialtyId);
        return intent;
    }

    @ProvidePresenter
    EmployeeActivityPresenter provideEmployeeActivityPresenter() {
        int specialtyId = getIntent().getIntExtra(EXTRA_SPECIALTY_ID, SPECIALTY_NOT_DEFINED);
        return new EmployeeActivityPresenter(specialtyId);
    }

    @LayoutRes
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }

    @Override
    public void inject() {
        App.getEmployeeComponent().inject(this);
    }

    @Override
    public Navigator createNavigator() {
        return new EmployeeActivityNavigator(this, getSupportFragmentManager());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState, isChangingConfigurations: " + isChangingConfigurations());

        // удаление DetailsFragment, чтобы не восстанавливался в портретном режиме
        if (isChangingConfigurations() && getResources().getConfiguration().orientation == ORIENTATION_PORTRAIT) {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.detail_fragment_container);
            if (fragment != null && fragment instanceof DetailsFragment) {
                getSupportFragmentManager().beginTransaction()
                        .remove(fragment)
                        .commit();
            }
        }
        super.onSaveInstanceState(outState);
    }
}