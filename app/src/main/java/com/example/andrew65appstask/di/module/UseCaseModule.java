package com.example.andrew65appstask.di.module;

import android.support.annotation.NonNull;

import com.example.andrew65appstask.data.Repository;
import com.example.andrew65appstask.di.scope.AppScope;
import com.example.andrew65appstask.domain.GetEmployeeDetails;
import com.example.andrew65appstask.domain.GetEmployees;
import com.example.andrew65appstask.domain.GetSpecialties;
import com.example.andrew65appstask.domain.UpdateData;

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
