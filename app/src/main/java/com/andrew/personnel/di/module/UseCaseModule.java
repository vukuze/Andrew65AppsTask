package com.andrew.personnel.di.module;

import android.support.annotation.NonNull;

import com.andrew.personnel.data.Repository;
import com.andrew.personnel.di.scope.AppScope;
import com.andrew.personnel.domain.GetEmployeeDetails;
import com.andrew.personnel.domain.GetEmployees;
import com.andrew.personnel.domain.GetSpecialties;
import com.andrew.personnel.domain.UpdateData;

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
