package com.andrew.personnel.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.andrew.personnel.di.scope.AppScope;
import com.andrew.personnel.data.Models;

import dagger.Module;
import dagger.Provides;
import io.requery.Persistable;
import io.requery.android.sqlite.DatabaseSource;
import io.requery.reactivex.ReactiveEntityStore;
import io.requery.reactivex.ReactiveSupport;
import io.requery.sql.Configuration;
import io.requery.sql.EntityDataStore;

@Module(includes = AppModule.class)
public class DatabaseModule {

    private ReactiveEntityStore<Persistable> db = null;

    public DatabaseModule(@NonNull Context appContext) {
        DatabaseSource source = new DatabaseSource(appContext, Models.DEFAULT, 1);
        Configuration configuration = source.getConfiguration();
        db = ReactiveSupport.toReactiveStore(new EntityDataStore<Persistable>(configuration));
    }

    @Provides
    @AppScope
    ReactiveEntityStore<Persistable> provideDatabase() {
        return db;
    }
}
