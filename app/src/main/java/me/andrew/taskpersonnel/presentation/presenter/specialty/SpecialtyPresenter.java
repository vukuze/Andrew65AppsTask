package me.andrew.taskpersonnel.presentation.presenter.specialty;

import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.andrew.taskpersonnel.App;
import me.andrew.taskpersonnel.domain.GetSpecialties;
import me.andrew.taskpersonnel.navigation.Screens;
import me.andrew.taskpersonnel.presentation.presenter.BasePresenter;
import me.andrew.taskpersonnel.presentation.view.specialty.SpecialtyView;

@InjectViewState
public class SpecialtyPresenter extends BasePresenter<SpecialtyView> {

    private static final String LOADING_SPECIALTIES_ERROR = "Произошла ошибка при загрузке данных";

    @Inject
    GetSpecialties getSpecialties;

    @Override
    public void inject() {
        App.getSpecialtyComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        setRequestDisposable(getSpecialties
                .executeUseCase(new GetSpecialties.RequestValues())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(specialties -> getViewState().updateItems(specialties),
                        throwable -> {
                            router.showSystemMessage(LOADING_SPECIALTIES_ERROR);
                            getViewState().updateItems(new ArrayList<>());
                        }));
    }

    public void onClick(int specialtyId) {
        router.navigateTo(Screens.EMPLOYEE_ACTIVITY, specialtyId);
    }
}