package me.andrew.taskpersonnel.di.module.specialty;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import me.andrew.taskpersonnel.data.Repository;
import me.andrew.taskpersonnel.di.scope.SpecialtyScope;
import me.andrew.taskpersonnel.domain.GetSpecialties;

@Module
public class SpecialtyUseCaseModule {
    @Provides
    @SpecialtyScope
    GetSpecialties provideSpecialties(@NonNull Repository repository) {
        return new GetSpecialties(repository);
    }
}
