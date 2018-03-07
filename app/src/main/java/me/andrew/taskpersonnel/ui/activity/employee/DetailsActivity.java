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
import me.andrew.taskpersonnel.navigation.DetailsActivityNavigator;
import me.andrew.taskpersonnel.presentation.presenter.employee.DetailsActivityPresenter;
import me.andrew.taskpersonnel.presentation.view.employee.DetailsActivityView;
import me.andrew.taskpersonnel.ui.activity.BaseActivity;
import me.andrew.taskpersonnel.ui.fragment.employee.DetailsFragment;
import ru.terrakok.cicerone.Navigator;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;
import static me.andrew.taskpersonnel.ui.fragment.employee.EmployeeFragment.EMPLOYEE_NOT_DEFINED;

public class DetailsActivity extends BaseActivity implements DetailsActivityView {

    private static final String TAG = "DetailsActivity";
    private static final String EXTRA_EMPLOYEE_ID = "employee_Id";

    @InjectPresenter
    DetailsActivityPresenter detailsActivityPresenter;

    public static Intent newIntent(Context context, int employeeId) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(EXTRA_EMPLOYEE_ID, employeeId);
        return intent;
    }

    @LayoutRes
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }

    @ProvidePresenter
    DetailsActivityPresenter provideDetailsActivityPresenter() {
        int employeeId = getIntent().getIntExtra(EXTRA_EMPLOYEE_ID, EMPLOYEE_NOT_DEFINED);
        return new DetailsActivityPresenter(employeeId);
    }

    @Override
    public void inject() {
        App.getDetailsComponent().inject(this);
    }

    @Override
    public Navigator createNavigator() {
        return new DetailsActivityNavigator(this, getSupportFragmentManager(), R.id.fragmentContainer);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
            finish();
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState, isChangingConfigurations: " + isChangingConfigurations());

        // удаление DetailsFragment, чтобы не восстанавливался в ландшафтном режиме
        if (isChangingConfigurations() && getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
            if (fragment != null && fragment instanceof DetailsFragment) {
                getSupportFragmentManager().beginTransaction()
                        .remove(fragment)
                        .commit();
            }
        }

        super.onSaveInstanceState(outState);
    }
}
