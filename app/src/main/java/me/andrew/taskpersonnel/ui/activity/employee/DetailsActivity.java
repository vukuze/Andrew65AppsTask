package me.andrew.taskpersonnel.ui.activity.employee;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import me.andrew.taskpersonnel.App;
import me.andrew.taskpersonnel.R;
import me.andrew.taskpersonnel.navigation.DetailsActivityNavigator;
import me.andrew.taskpersonnel.presentation.presenter.employee.DetailsActivityPresenter;
import me.andrew.taskpersonnel.presentation.view.employee.DetailsActivityView;
import me.andrew.taskpersonnel.ui.activity.BaseActivity;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;
import static me.andrew.taskpersonnel.ui.fragment.employee.EmployeeFragment.EMPLOYEE_NOT_DEFINED;

public class DetailsActivity extends BaseActivity implements DetailsActivityView {

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
    protected void onCreate(Bundle savedInstanceState) {
        boolean isLandscape = getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE;
        if (isLandscape) {
            finish();
        }
        super.onCreate(savedInstanceState);
        this.navigator = new DetailsActivityNavigator(this, getSupportFragmentManager(), R.id.fragmentContainer);
    }
}
