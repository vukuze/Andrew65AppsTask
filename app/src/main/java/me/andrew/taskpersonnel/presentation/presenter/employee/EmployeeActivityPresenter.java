package me.andrew.taskpersonnel.presentation.presenter.employee;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import me.andrew.taskpersonnel.App;
import me.andrew.taskpersonnel.navigation.Screens;
import me.andrew.taskpersonnel.presentation.presenter.BasePresenter;
import me.andrew.taskpersonnel.presentation.view.employee.EmployeeActivityView;
import me.andrew.taskpersonnel.ui.fragment.employee.DetailsFragment;
import me.andrew.taskpersonnel.ui.fragment.employee.EmployeeFragment;

public class EmployeeActivityPresenter extends BasePresenter<EmployeeActivityView> {

    private static final String TAG = "EmployeeActiviPresenter";

    private EmployeeFragment.RequestData employeeRequestData = new EmployeeFragment.RequestData();

    public void setSpecialtyId(int specialtyId) {
        this.employeeRequestData.setSpecialtyId(specialtyId);
    }

    public void setEmployeeId(int employeeId) {
        this.employeeRequestData.setEmployeeId(employeeId);
    }

    @Override
    public void inject() {
        App.getEmployeeComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        router.replaceScreen(Screens.EMPLOYEE_FRAGMENT, employeeRequestData);
    }

    public void handleFragmentRestore(FragmentManager fragmentManager, boolean isLandscape) {
        if (fragmentManager.getFragments().isEmpty())
            return;

        Log.d(TAG, "fragmentManager.getBackStackEntryCount: " + fragmentManager.getBackStackEntryCount());
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
            Log.d(TAG, "fragmentManager.getBackStackEntryAt(" + i + "): " +
                    fragmentManager.getBackStackEntryAt(i).getName());
        }

//        EmployeeFragment employeeFragment = null;
        DetailsFragment detailsFragment = null;
        for (Fragment fragment : fragmentManager.getFragments()) {
//            Log.d(TAG, "fragment: " + fragment.getClass().getSimpleName());
//            if (fragment instanceof EmployeeFragment)
//                employeeFragment = (EmployeeFragment) fragment;
            if (fragment instanceof DetailsFragment)
                detailsFragment = (DetailsFragment) fragment;
        }

        if (isLandscape) {
            if (detailsFragment != null) {
                router.exit();
            }
//            if (employeeFragment == null) {
//                if (detailsFragment != null) {
////                    fragmentManager.beginTransaction()
////                            .remove(detailsFragment)
////                            .commit();
//                    detailsFragment.onBackPressed();
//                    for (Fragment fragment : fragmentManager.getFragments()) {
//                        Log.d(TAG, "fragment: " + fragment.getClass().getSimpleName());
//                    }
//                }
//                router.replaceScreen(Screens.EMPLOYEE_FRAGMENT, employeeRequestData);
//            }
//        } else {
//            if (employeeFragment == null) {
//                if (detailsFragment != null)
//                    fragments.remove(detailsFragment);
//            }
        }
    }
}