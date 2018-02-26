package me.andrew.taskpersonnel.di.module;

import android.support.annotation.NonNull;

import me.andrew.taskpersonnel.data.Repository;
import me.andrew.taskpersonnel.di.scope.AppScope;
import me.andrew.taskpersonnel.domain.GetEmployeeDetails;
import me.andrew.taskpersonnel.domain.GetEmployees;
import me.andrew.taskpersonnel.domain.GetSpecialties;
import me.andrew.taskpersonnel.domain.UpdateData;

import dagger.Module;
import dagger.Provides;

@Module
public class UseCaseModule {
    // TODO: 15.02.2018 Проверить Scope
    @Provides
    @AppScope
    UpdateData provideNetworkData(@NonNull Repository repository) {
        return new UpdateData(repository);
    }

    @Provides
    @AppScope
    GetSpecialties provideSpecialties(@NonNull Repository repository) {
        return new GetSpecialties(repository);
    }


    @Provides
    @AppScope
    GetEmployees provideEmployees(@NonNull Repository repository) {
        return new GetEmployees(repository);
    }

    @Provides
    @AppScope
    GetEmployeeDetails provideEmployeeDetails(@NonNull Repository repository) {
        return new GetEmployeeDetails(repository);
    }
}
