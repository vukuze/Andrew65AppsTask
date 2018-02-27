package me.andrew.taskpersonnel.ui.activity.employee;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import me.andrew.taskpersonnel.App;
import me.andrew.taskpersonnel.R;
import me.andrew.taskpersonnel.navigation.EmployeeActivityNavigator;
import me.andrew.taskpersonnel.presentation.presenter.employee.EmployeeActivityPresenter;
import me.andrew.taskpersonnel.presentation.view.employee.EmployeeActivityView;
import me.andrew.taskpersonnel.ui.activity.BaseActivity;

import static me.andrew.taskpersonnel.ui.fragment.specialty.SpecialtyFragment.SPECIALTY_NOT_DEFINED;

public class EmployeeActivity extends BaseActivity implements EmployeeActivityView {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.navigator = new EmployeeActivityNavigator(this, getSupportFragmentManager());
    }
}
