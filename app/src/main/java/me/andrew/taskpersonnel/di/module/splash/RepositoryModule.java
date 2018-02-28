package me.andrew.taskpersonnel.di.module.splash;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import io.requery.Persistable;
import io.requery.reactivex.ReactiveEntityStore;
import me.andrew.taskpersonnel.data.Repository;
import me.andrew.taskpersonnel.di.scope.AppScope;

@Module
public class RepositoryModule {
    @Provides
    @AppScope
    Repository provideRepository(@NonNull ReactiveEntityStore<Persistable> db) {
        return new Repository(db);
    }
}
