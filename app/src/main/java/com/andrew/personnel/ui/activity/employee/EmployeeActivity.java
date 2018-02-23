package com.andrew.personnel.ui.activity.employee;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.andrew.personnel.App;
import com.andrew.personnel.R;
import com.andrew.personnel.navigation.EmployeeActivityNavigator;
import com.andrew.personnel.presentation.presenter.employee.EmployeeActivityPresenter;
import com.andrew.personnel.presentation.view.employee.EmployeeActivityView;
import com.andrew.personnel.ui.activity.BaseActivity;

import static com.andrew.personnel.ui.fragment.employee.EmployeeFragment.SPECIALTY_NOT_DEFINED;

public class EmployeeActivity extends BaseActivity implements EmployeeActivityView {

    //    private static final String TAG = "EmployeeActivity";
    private static final String EXTRA_SPECIALTY_ID = "specialty_id";

    @InjectPresenter
    EmployeeActivityPresenter employeeActivityPresenter;

    public static Intent newIntent(Context context, int specialtyId) {
        Intent intent = new Intent(context, EmployeeActivity.class);
        intent.putExtra(EXTRA_SPECIALTY_ID, specialtyId);
        return intent;
    }

    public void setEmployeeId(int employeeId) {
        employeeActivityPresenter.setEmployeeId(employeeId);
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

        boolean isLandscape = findViewById(R.id.detail_fragment_container) != null;
        this.navigator = new EmployeeActivityNavigator(this, getSupportFragmentManager(), isLandscape);

        employeeActivityPresenter.handleFragmentRestore(getSupportFragmentManager(), isLandscape);

        int specialtyId = getIntent().getIntExtra(EXTRA_SPECIALTY_ID, SPECIALTY_NOT_DEFINED);
        employeeActivityPresenter.setSpecialtyId(specialtyId);
    }
}