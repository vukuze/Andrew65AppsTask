package me.andrew.taskpersonnel.di.module;

import android.support.annotation.NonNull;

import me.andrew.taskpersonnel.di.scope.AppScope;
import me.andrew.taskpersonnel.data.Repository;
import me.andrew.taskpersonnel.data.network.SixtyFiveAppsRestService;

import dagger.Module;
import dagger.Provides;
import io.requery.Persistable;
import io.requery.reactivex.ReactiveEntityStore;

/**
 *
 */

@Module
public class RepositoryModule {
    @Provides
    @AppScope
    Repository provideRepository(@NonNull ReactiveEntityStore<Persistable> db, @NonNull SixtyFiveAppsRestService service) {
        return new Repository(db, service);
    }
}
