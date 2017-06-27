package com.example.andrew65appstask.dagger.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.andrew65appstask.Models;
import com.example.andrew65appstask.dagger.module.AppModule;
import com.example.andrew65appstask.dagger.scope.AppScope;

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
