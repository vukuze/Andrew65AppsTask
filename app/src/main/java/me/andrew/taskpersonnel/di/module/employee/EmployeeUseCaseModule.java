package me.andrew.taskpersonnel.di.module.employee;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import me.andrew.taskpersonnel.data.Repository;
import me.andrew.taskpersonnel.di.scope.EmployeeScope;
import me.andrew.taskpersonnel.domain.GetEmployees;

@Module
public class EmployeeUseCaseModule {
    @Provides
    @EmployeeScope
    GetEmployees provideEmployees(@NonNull Repository repository) {
        return new GetEmployees(repository);
    }
}
