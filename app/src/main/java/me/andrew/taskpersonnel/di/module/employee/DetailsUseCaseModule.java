package me.andrew.taskpersonnel.di.module.employee;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import me.andrew.taskpersonnel.data.Repository;
import me.andrew.taskpersonnel.di.scope.DetailsScope;
import me.andrew.taskpersonnel.domain.GetEmployeeDetails;

@Module
public class DetailsUseCaseModule {
    @Provides
    @DetailsScope
    GetEmployeeDetails provideEmployeeDetails(@NonNull Repository repository) {
        return new GetEmployeeDetails(repository);
    }
}
