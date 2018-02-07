package com.example.andrew65appstask.ui.activity;

import android.os.Bundle;

import com.example.andrew65appstask.App;
import com.example.andrew65appstask.R;
import com.example.andrew65appstask.navigation.EmployeeActivityNavigator;

import ru.terrakok.cicerone.Navigator;

public class EmployeeActivity extends BaseActivity {
//        implements EmployeeFragment.Callbacks {

//    private static final String EXTRA_SPECIALTY_ID = "specialty_id";
//    public static Intent newIntent(Context context, int specialtyId) {
//        Intent intent = new Intent(context, EmployeeActivity.class);
//        intent.putExtra(EXTRA_SPECIALTY_ID, specialtyId);
//        return intent;
//    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }

    @Override
    protected Navigator createNavigator() {
        return new EmployeeActivityNavigator(this, R.id.fragmentContainer);
    }

    @Override
    public void inject() {
        App.getEmployeeComponent().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        int specialtyId = getIntent().getIntExtra(EXTRA_SPECIALTY_ID, 0);
//        navigator.applyCommand(new Replace(Screens.EMPLOYEE_FRAGMENT, specialtyId));
    }

//    @Override
//    public void onEmployeeSelected(int employeeId) {
//        boolean isLandscape = findViewById(R.id.detail_fragment_container) != null;
//
//        if (isLandscape) {
//            navigator.applyCommand(new Replace(Screens.DETAILS_FRAGMENT, employeeId));
//        } else {
//            navigator.applyCommand(new Forward(Screens.DETAILS_ACTIVITY, employeeId));
//        }
//    }
}