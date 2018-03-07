package me.andrew.taskpersonnel.presentation.presenter.employee;

import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.andrew.taskpersonnel.App;
import me.andrew.taskpersonnel.domain.GetEmployeeDetails;
import me.andrew.taskpersonnel.presentation.presenter.BasePresenter;
import me.andrew.taskpersonnel.presentation.view.employee.DetailsView;

import static me.andrew.taskpersonnel.ui.fragment.employee.EmployeeFragment.EMPLOYEE_NOT_DEFINED;

@InjectViewState
public class DetailsPresenter extends BasePresenter<DetailsView> {

    @Inject
    GetEmployeeDetails getEmployeeDetails;

    private int employeeId = EMPLOYEE_NOT_DEFINED;

    public DetailsPresenter(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public void inject() {
        App.getDetailsComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        setDisposable(getEmployeeDetails.executeUseCase(new GetEmployeeDetails.RequestValues(employeeId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(employee -> getViewState().updateItems(employee)));
    }
}