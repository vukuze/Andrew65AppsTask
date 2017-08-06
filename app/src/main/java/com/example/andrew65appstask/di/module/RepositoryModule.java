package com.example.andrew65appstask.di.module;

import android.support.annotation.NonNull;

import com.example.andrew65appstask.di.scope.AppScope;
import com.example.andrew65appstask.data.Repository;
import com.example.andrew65appstask.data.network.SixtyFiveAppsRestService;

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
