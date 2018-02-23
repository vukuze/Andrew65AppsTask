package com.andrew.personnel.di.module;

import android.support.annotation.NonNull;

import com.andrew.personnel.di.scope.AppScope;
import com.andrew.personnel.data.Repository;
import com.andrew.personnel.data.network.SixtyFiveAppsRestService;

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
